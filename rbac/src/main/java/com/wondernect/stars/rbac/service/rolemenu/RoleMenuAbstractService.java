package com.wondernect.stars.rbac.service.rolemenu;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.rbac.dto.RoleMenuResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;
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
    public void add(RoleMenuRequestDTO roleMenuRequestDTO) {
        Role role = roleManager.findByCode(roleMenuRequestDTO.getRoleCode());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findByCode(roleMenuRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleCodeAndMenuCode(roleMenuRequestDTO.getRoleCode(), roleMenuRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(roleMenu)) {
            super.save(
                    new RoleMenu(
                            roleMenuRequestDTO.getRoleCode(),
                            roleMenuRequestDTO.getMenuCode()
                    )
            );
        }
    }

    @Transactional
    public void edit(RoleMenuRequestDTO roleMenuRequestDTO) {
        Role role = roleManager.findByCode(roleMenuRequestDTO.getRoleCode());
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findByCode(roleMenuRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleCodeAndMenuCode(roleMenuRequestDTO.getRoleCode(), roleMenuRequestDTO.getMenuCode());
        if (ESObjectUtils.isNull(roleMenu)) {
            throw new BusinessException("角色菜单关系不存在");
        }
        roleMenu.setLimitable(roleMenuRequestDTO.getLimitable());
        roleMenu.setStartTime(roleMenuRequestDTO.getStartTime());
        roleMenu.setEndTime(roleMenuRequestDTO.getEndTime());
        super.save(roleMenu);
    }

    @Transactional
    public void delete(RoleMenuRequestDTO roleMenuRequestDTO) {
        RoleMenu roleMenu = roleMenuManager.findByRoleCodeAndMenuCode(roleMenuRequestDTO.getRoleCode(), roleMenuRequestDTO.getMenuCode());
        if (ESObjectUtils.isNotNull(roleMenu)) {
            roleMenuOperationManager.deleteAllByRoleCodeAndMenuCode(roleMenuRequestDTO.getRoleCode(), roleMenuRequestDTO.getMenuCode());
            super.deleteById(roleMenu.getId());
        }
    }

    public RoleMenuTreeResponseDTO tree(String roleCode, String menuCode) {
        Role role = roleManager.findByCode(roleCode);
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("角色不存在");
        }
        Menu menu = menuManager.findByCode(menuCode);
        if (ESObjectUtils.isNull(menu)) {
            throw new BusinessException("菜单不存在");
        }
        RoleMenu roleMenu = roleMenuManager.findByRoleCodeAndMenuCode(roleCode, menu.getCode());
        RoleMenuTreeResponseDTO roleMenuTreeResponseDTO;
        if (ESObjectUtils.isNotNull(roleMenu)) {
            roleMenuTreeResponseDTO = new RoleMenuTreeResponseDTO(
                    menu.getCode(),
                    menu.getName(),
                    true,
                    roleMenu.getLimitable(),
                    roleMenu.getStartTime(),
                    roleMenu.getEndTime(),
                    null
            );
        } else {
            roleMenuTreeResponseDTO = new RoleMenuTreeResponseDTO(
                    menu.getCode(),
                    menu.getName(),
                    false,
                    null,
                    null,
                    null,
                    null
            );
        }
        return tree(roleCode, roleMenuTreeResponseDTO.getCode(), roleMenuTreeResponseDTO);
    }

    private RoleMenuTreeResponseDTO tree(String roleCode, String menuCode, RoleMenuTreeResponseDTO roleMenuTreeResponseDTO) {
        List<RoleMenuTreeResponseDTO> roleMenuTreeResponseDTOList = new ArrayList<>();
        Criteria<Menu> menuCriteria = new Criteria<>();
        menuCriteria.add(Restrictions.eq("parentMenuCode", menuCode));
        List<Menu> menuList = menuManager.findAll(menuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(menuList)) {
            for (Menu menu : menuList) {
                RoleMenu roleMenu = roleMenuManager.findByRoleCodeAndMenuCode(roleCode, menu.getCode());
                if (ESObjectUtils.isNotNull(roleMenu)) {
                    roleMenuTreeResponseDTOList.add(
                            new RoleMenuTreeResponseDTO(
                                    menu.getCode(),
                                    menu.getName(),
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
                                    menu.getCode(),
                                    menu.getName(),
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
                tree(roleCode, roleMenuTreeResponseDTOLoop.getCode(), roleMenuTreeResponseDTOLoop);
            }
        }
        return roleMenuTreeResponseDTO;
    }

    @Override
    public RoleMenuResponseDTO generate(RoleMenu entity) {
        return null;
    }
}
