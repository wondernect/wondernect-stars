package com.wondernect.stars.rbac.service.menu;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.OperationManager;
import com.wondernect.stars.rbac.manager.RoleMenuManager;
import com.wondernect.stars.rbac.manager.RoleMenuOperationManager;
import com.wondernect.stars.rbac.model.Menu;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: MenuService
 * Author: chenxun
 * Date: 2020-02-21 14:04
 * Description:
 */
public abstract class MenuAbstractService extends BaseStringService<MenuResponseDTO, Menu> implements MenuInterface {

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private OperationManager operationManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    public MenuResponseDTO create(SaveMenuRequestDTO saveMenuRequestDTO) {
        Menu parentMenu = super.findEntityById(saveMenuRequestDTO.getParentMenuId());
        if (ESObjectUtils.isNull(parentMenu)) {
            throw new BusinessException("父级菜单不存在");
        }
        Menu menu = menuManager.findByCode(saveMenuRequestDTO.getCode());
        if (ESObjectUtils.isNotNull(menu)) {
            throw new BusinessException("菜单代码已存在");
        }
        if (ESObjectUtils.isNull(saveMenuRequestDTO.getWeight())) {
            saveMenuRequestDTO.setWeight(0);
        }
        return super.save(
                new Menu(
                        saveMenuRequestDTO.getName(),
                        saveMenuRequestDTO.getCode(),
                        saveMenuRequestDTO.getRoute(),
                        saveMenuRequestDTO.getDescription(),
                        saveMenuRequestDTO.getEditable(),
                        saveMenuRequestDTO.getDeletable(),
                        saveMenuRequestDTO.getWeight(),
                        saveMenuRequestDTO.getParentMenuId()
                )
        );
    }

    @Transactional
    public MenuResponseDTO update(String id, SaveMenuRequestDTO saveMenuRequestDTO) {
        Menu parentMenu = super.findEntityById(saveMenuRequestDTO.getParentMenuId());
        if (ESObjectUtils.isNull(parentMenu)) {
            throw new BusinessException("父级菜单不存在");
        }
        Menu menu = super.findEntityById(id);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        if (!menu.getEditable()) {
            throw new BusinessException("菜单不可编辑");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveMenuRequestDTO, menu);
        return super.save(menu);
    }

    @Transactional
    public void deleteById(String id) {
        Menu menu = super.findEntityById(id);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        if (!menu.getDeletable()) {
            throw new BusinessException("菜单不可删除");
        }
        if (countByParentMenuId(menu.getCode()) > 0) {
            throw new BusinessException("请先删除子菜单");
        }
        operationManager.deleteAllByMenuId(menu.getId());
        roleMenuManager.deleteAllByMenuId(menu.getId());
        roleMenuOperationManager.deleteAllByMenuId(menu.getId());
        super.deleteById(id);
    }

    public long countByParentMenuId(String parentMenuId) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuId", parentMenuId));
        return super.count(menuCriteria);
    }

    public List<MenuResponseDTO> list(ListMenuRequestDTO listMenuRequestDTO) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuId", listMenuRequestDTO.getParentMenuId()));
        return super.findAll(menuCriteria, listMenuRequestDTO.getSortDataList());
    }

    public PageResponseData<MenuResponseDTO> page(PageMenuRequestDTO pageMenuRequestDTO) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuId", pageMenuRequestDTO.getParentMenuId()));
        return super.findAll(menuCriteria, pageMenuRequestDTO.getPageRequestData());
    }

    public MenuTreeResponseDTO tree(String id) {
        Menu menu = super.findEntityById(id);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Menu parentMenu = super.findEntityById(menu.getParentMenuId());
        if (ESObjectUtils.isNull(parentMenu)) {
            throw new BusinessException("父级菜单不存在");
        }
        MenuTreeResponseDTO menuTreeResponseDTO = new MenuTreeResponseDTO(
                menu.getId(),
                menu.getName(),
                menu.getCode(),
                menu.getRoute(),
                menu.getDescription(),
                menu.getEditable(),
                menu.getDeletable(),
                menu.getWeight(),
                menu.getParentMenuId(),
                parentMenu.getName(),
                parentMenu.getCode(),
                parentMenu.getRoute(),
                null
        );
        return tree(menuTreeResponseDTO.getParentMenuId(), menuTreeResponseDTO);
    }

    private MenuTreeResponseDTO tree(String parentMenuId, MenuTreeResponseDTO menuTreeResponseDTO) {
        List<MenuTreeResponseDTO> menuTreeResponseDTOList = new ArrayList<>();
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuId", parentMenuId));
        List<Menu> menuList = super.findAllEntity(menuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                Menu parentMenu = super.findEntityById(menu.getParentMenuId());
                menuTreeResponseDTOList.add(
                        new MenuTreeResponseDTO(
                                menu.getId(),
                                menu.getName(),
                                menu.getCode(),
                                menu.getRoute(),
                                menu.getDescription(),
                                menu.getEditable(),
                                menu.getDeletable(),
                                menu.getWeight(),
                                menu.getParentMenuId(),
                                parentMenu.getName(),
                                parentMenu.getCode(),
                                parentMenu.getRoute(),
                                null)
                );
            }
        }
        menuTreeResponseDTO.setChildList(menuTreeResponseDTOList);
        if (CollectionUtils.isNotEmpty(menuTreeResponseDTOList)) {
            for (MenuTreeResponseDTO menuTreeResponseDTOLoop : menuTreeResponseDTOList) {
                tree(menuTreeResponseDTOLoop.getParentMenuId(), menuTreeResponseDTOLoop);
            }
        }
        return menuTreeResponseDTO;
    }

    public MenuResponseDTO generate(Menu menu) {
        MenuResponseDTO menuResponseDTO = new MenuResponseDTO();
        ESBeanUtils.copyProperties(menu, menuResponseDTO);
        menuResponseDTO.setId(menu.getId());
        Menu parentMenu = super.findEntityById(menu.getParentMenuId());
        menuResponseDTO.setParentMenuName(ESObjectUtils.isNotNull(parentMenu) ? parentMenu.getName() : null);
        menuResponseDTO.setParentMenuCode(ESObjectUtils.isNotNull(parentMenu) ? parentMenu.getCode() : null);
        menuResponseDTO.setParentMenuRoute(ESObjectUtils.isNotNull(parentMenu) ? parentMenu.getRoute() : null);
        return menuResponseDTO;
    }
}
