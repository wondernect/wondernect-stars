package com.wondernect.stars.rbac.service.menu;

import com.wondernect.elements.common.exception.BusinessException;
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
import org.hibernate.criterion.MatchMode;
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
        Menu parentMenu = menuManager.findByCode(saveMenuRequestDTO.getParentMenuCode());
        if (ESObjectUtils.isNull(parentMenu)) {
            throw new BusinessException("父级菜单不存在");
        }
        Menu menu = menuManager.findByCode(saveMenuRequestDTO.getCode());
        if (ESObjectUtils.isNotNull(menu)) {
            throw new BusinessException("菜单已存在");
        }
        if (ESObjectUtils.isNull(saveMenuRequestDTO.getWeight())) {
            saveMenuRequestDTO.setWeight(0);
        }
        return super.save(
                new Menu(
                        saveMenuRequestDTO.getCode(),
                        saveMenuRequestDTO.getName(),
                        saveMenuRequestDTO.getDescription(),
                        saveMenuRequestDTO.getEditable(),
                        saveMenuRequestDTO.getDeletable(),
                        saveMenuRequestDTO.getWeight(),
                        saveMenuRequestDTO.getParentMenuCode()
                )
        );
    }

    @Transactional
    public MenuResponseDTO update(String id, SaveMenuRequestDTO saveMenuRequestDTO) {
        Menu parentMenu = menuManager.findByCode(saveMenuRequestDTO.getParentMenuCode());
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
        menu.setName(saveMenuRequestDTO.getName());
        menu.setDescription(saveMenuRequestDTO.getDescription());
        menu.setEditable(saveMenuRequestDTO.getEditable());
        menu.setDeletable(saveMenuRequestDTO.getDeletable());
        if (ESObjectUtils.isNotNull(saveMenuRequestDTO.getWeight())) {
            menu.setWeight(saveMenuRequestDTO.getWeight());
        }
        menu.setParentMenuCode(saveMenuRequestDTO.getParentMenuCode());
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
        if (countByParentMenuCode(menu.getCode()) > 0) {
            throw new BusinessException("请先删除子菜单");
        }
        operationManager.deleteAllByMenuCode(menu.getCode());
        roleMenuManager.deleteAllByMenuCode(menu.getCode());
        roleMenuOperationManager.deleteAllByMenuCode(menu.getCode());
        super.deleteById(id);
    }

    public MenuResponseDTO findByCode(String code) {
        Menu menu = menuManager.findByCode(code);
        if (ESObjectUtils.isNull(menu)) {
            return null;
        }
        return generate(menu);
    }

    public long countByParentMenuCode(String parentMenuCode) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuCode", parentMenuCode));
        return super.count(menuCriteria);
    }

    public List<MenuResponseDTO> list(ListMenuRequestDTO listMenuRequestDTO) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(
                Restrictions.and(
                        Restrictions.eq("parentMenuCode", listMenuRequestDTO.getParentMenuCode()),
                        Restrictions.or(
                                Restrictions.like("code", listMenuRequestDTO.getValue(), MatchMode.ANYWHERE),
                                Restrictions.like("name", listMenuRequestDTO.getValue(), MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(menuCriteria, listMenuRequestDTO.getSortDataList());
    }

    public PageResponseData<MenuResponseDTO> page(PageMenuRequestDTO pageMenuRequestDTO) {
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(
                Restrictions.and(
                        Restrictions.eq("parentMenuCode", pageMenuRequestDTO.getParentMenuCode()),
                        Restrictions.or(
                                Restrictions.like("code", pageMenuRequestDTO.getValue(), MatchMode.ANYWHERE),
                                Restrictions.like("name", pageMenuRequestDTO.getValue(), MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(menuCriteria, pageMenuRequestDTO.getPageRequestData());
    }

    public MenuTreeResponseDTO tree(String code) {
        Menu menu = menuManager.findByCode(code);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        Menu parentMenu = menuManager.findByCode(menu.getParentMenuCode());
        if (ESObjectUtils.isNull(parentMenu)) {
            throw new BusinessException("父级菜单不存在");
        }
        MenuTreeResponseDTO menuTreeResponseDTO = new MenuTreeResponseDTO(
                menu.getId(),
                menu.getCode(),
                menu.getName(),
                menu.getDescription(),
                menu.getEditable(),
                menu.getDeletable(),
                menu.getWeight(),
                menu.getParentMenuCode(),
                parentMenu.getName(),
                null
        );
        return tree(menuTreeResponseDTO.getCode(), menuTreeResponseDTO);
    }

    private MenuTreeResponseDTO tree(String code, MenuTreeResponseDTO menuTreeResponseDTO) {
        List<MenuTreeResponseDTO> menuTreeResponseDTOList = new ArrayList<>();
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuCode", code));
        List<Menu> menuList = super.findAllEntity(menuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                Menu parentMenu = menuManager.findByCode(menu.getParentMenuCode());
                menuTreeResponseDTOList.add(
                        new MenuTreeResponseDTO(
                                menu.getId(),
                                menu.getCode(),
                                menu.getName(),
                                menu.getDescription(),
                                menu.getEditable(),
                                menu.getDeletable(),
                                menu.getWeight(),
                                menu.getParentMenuCode(),
                                parentMenu.getName(),
                                null)
                );
            }
        }
        menuTreeResponseDTO.setChildList(menuTreeResponseDTOList);
        if (CollectionUtils.isNotEmpty(menuTreeResponseDTOList)) {
            for (MenuTreeResponseDTO menuTreeResponseDTOLoop : menuTreeResponseDTOList) {
                tree(menuTreeResponseDTOLoop.getCode(), menuTreeResponseDTOLoop);
            }
        }
        return menuTreeResponseDTO;
    }

    public MenuResponseDTO generate(Menu menu) {
        Menu parentMenu = menuManager.findByCode(menu.getParentMenuCode());
        return new MenuResponseDTO(
                menu.getId(),
                menu.getCode(),
                menu.getName(),
                menu.getDescription(),
                menu.getEditable(),
                menu.getDeletable(),
                menu.getWeight(),
                menu.getParentMenuCode(),
                ESObjectUtils.isNotNull(parentMenu) ? parentMenu.getName() : null
        );
    }
}
