package com.wondernect.stars.rbac.service.rolemenu;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.rbac.dto.rolemenu.*;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.RoleMenuManager;
import com.wondernect.stars.rbac.manager.RoleMenuOperationManager;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.RoleMenu;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuService
 * Author: chenxun
 * Date: 2020-02-21 14:05
 * Description:
 */
public abstract class RoleMenuAbstractService extends BaseStringService<RoleMenuResponseDTO, RoleMenu> implements RoleMenuInterface {

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Autowired
    private RoleMenuOperationManager roleMenuOperationManager;

    @Transactional
    @Override
    public void add(RoleMenuRequestDTO roleMenuRequestDTO) {
        Role role = roleManager.findById(roleMenuRequestDTO.getRoleId());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findById(roleMenuRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleMenuRequestDTO.getRoleId(), roleMenuRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(roleMenu)) {
            super.saveEntity(
                    new RoleMenu(
                            roleMenuRequestDTO.getRoleId(),
                            roleMenuRequestDTO.getMenuId(),
                            roleMenuRequestDTO.getLimitable(),
                            roleMenuRequestDTO.getStartTime(),
                            roleMenuRequestDTO.getEndTime()
                    )
            );
        }
    }

    @Transactional
    @Override
    public void edit(RoleMenuRequestDTO roleMenuRequestDTO) {
        Role role = roleManager.findById(roleMenuRequestDTO.getRoleId());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findById(roleMenuRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleMenuRequestDTO.getRoleId(), roleMenuRequestDTO.getMenuId());
        if (ESObjectUtils.isNull(roleMenu)) {
            throw new BusinessException("角色菜单关系不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(roleMenuRequestDTO, roleMenu);
        super.saveEntity(roleMenu);
    }

    @Transactional
    @Override
    public void delete(RoleMenuRequestDTO roleMenuRequestDTO) {
        RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleMenuRequestDTO.getRoleId(), roleMenuRequestDTO.getMenuId());
        if (ESObjectUtils.isNotNull(roleMenu)) {
            roleMenuOperationManager.deleteAllByRoleIdAndMenuId(roleMenuRequestDTO.getRoleId(), roleMenuRequestDTO.getMenuId());
            super.deleteById(roleMenu.getId());
        }
    }

    @Override
    public RoleMenuResponseDTO findByRoleIdAndMenuId(String roleId, String menuId) {
        Menu menu = menuManager.findById(menuId);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleId, menuId);
        if (ESObjectUtils.isNotNull(roleMenu)) {
            return new RoleMenuResponseDTO(
                    menu.getId(),
                    menu.getName(),
                    menu.getCode(),
                    menu.getRoute(),
                    true,
                    roleMenu.getLimitable(),
                    roleMenu.getStartTime(),
                    roleMenu.getEndTime()
            );
        } else {
            return new RoleMenuResponseDTO(
                    menu.getId(),
                    menu.getName(),
                    menu.getCode(),
                    menu.getRoute(),
                    false,
                    null,
                    null,
                    null
            );
        }
    }

    @Override
    public RoleMenuTreeResponseDTO tree(String roleId, String menuId) {
        Role role = roleManager.findById(roleId);
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findById(menuId);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleId, menuId);
        RoleMenuTreeResponseDTO roleMenuTreeResponseDTO;
        if (ESObjectUtils.isNotNull(roleMenu)) {
            roleMenuTreeResponseDTO = new RoleMenuTreeResponseDTO(
                    menu.getId(),
                    menu.getName(),
                    menu.getCode(),
                    menu.getRoute(),
                    true,
                    roleMenu.getLimitable(),
                    roleMenu.getStartTime(),
                    roleMenu.getEndTime(),
                    null
            );
        } else {
            roleMenuTreeResponseDTO = new RoleMenuTreeResponseDTO(
                    menu.getId(),
                    menu.getName(),
                    menu.getCode(),
                    menu.getRoute(),
                    false,
                    null,
                    null,
                    null,
                    null
            );
        }
        return tree(roleId, roleMenuTreeResponseDTO.getId(), roleMenuTreeResponseDTO);
    }

    private RoleMenuTreeResponseDTO tree(String roleId, String menuId, RoleMenuTreeResponseDTO roleMenuTreeResponseDTO) {
        List<RoleMenuTreeResponseDTO> roleMenuTreeResponseDTOList = new ArrayList<>();
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuId", menuId));
        List<Menu> menuList = menuManager.findAll(menuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                RoleMenu roleMenu = roleMenuManager.findByRoleIdAndMenuId(roleId, menu.getId());
                if (ESObjectUtils.isNotNull(roleMenu)) {
                    roleMenuTreeResponseDTOList.add(
                            new RoleMenuTreeResponseDTO(
                                    menu.getId(),
                                    menu.getName(),
                                    menu.getCode(),
                                    menu.getRoute(),
                                    true,
                                    roleMenu.getLimitable(),
                                    roleMenu.getStartTime(),
                                    roleMenu.getEndTime(),
                                    null
                            )
                    );
                } else {
                    roleMenuTreeResponseDTOList.add(
                            new RoleMenuTreeResponseDTO(
                                    menu.getId(),
                                    menu.getName(),
                                    menu.getCode(),
                                    menu.getRoute(),
                                    false,
                                    null,
                                    null,
                                    null,
                                    null
                            )
                    );
                }
            }
        }
        roleMenuTreeResponseDTO.setChildList(roleMenuTreeResponseDTOList);
        if (CollectionUtils.isNotEmpty(roleMenuTreeResponseDTOList)) {
            for (RoleMenuTreeResponseDTO roleMenuTreeResponseDTOLoop : roleMenuTreeResponseDTOList) {
                tree(roleId, roleMenuTreeResponseDTOLoop.getId(), roleMenuTreeResponseDTOLoop);
            }
        }
        return roleMenuTreeResponseDTO;
    }
}
