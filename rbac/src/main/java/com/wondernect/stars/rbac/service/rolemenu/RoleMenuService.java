package com.wondernect.stars.rbac.service.rolemenu;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESDateTimeUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.OperationAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.RoleAuthorityResponseDTO;
import com.wondernect.stars.rbac.model.*;
import com.wondernect.stars.rbac.service.menu.MenuService;
import com.wondernect.stars.rbac.service.operation.OperationService;
import com.wondernect.stars.rbac.service.role.RoleService;
import com.wondernect.stars.rbac.service.rolemenuoperation.RoleMenuOperationService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuService
 * Author: chenxun
 * Date: 2020-06-23 18:34
 * Description:
 */
@Service
public class RoleMenuService extends RoleMenuAbstractService {

    private static final Logger logger = LoggerFactory.getLogger(RoleMenuService.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private RoleMenuOperationService roleMenuOperationService;

    public RoleAuthorityResponseDTO roleAuthority(String roleId) {
        Role role = roleService.findEntityById(roleId);
        if (ESObjectUtils.isNull(role)) {
            throw new BusinessException("用户角色不存在,不可登录");
        }
        RoleAuthorityResponseDTO roleAuthorityResponseDTO = new RoleAuthorityResponseDTO();
        ESBeanUtils.copyProperties(role, roleAuthorityResponseDTO);
        roleAuthorityResponseDTO.setRoleId(roleId);
        roleAuthorityResponseDTO.setMenuList(roleAuthority(Arrays.asList(roleId)));
        return roleAuthorityResponseDTO;
    }

    public List<MenuAuthorityResponseDTO> roleAuthority(List<String> roleIdList) {
        Long currentTime = ESDateTimeUtils.getCurrentTimestamp();
        List<MenuAuthorityResponseDTO> menuAuthorityResponseDTOList = new ArrayList<>();
        // 菜单
        Criteria<RoleMenu> roleMenuCriteria = new Criteria<>();
        roleMenuCriteria.add(Restrictions.in("roleId", roleIdList));
        List<RoleMenu> roleMenuList = super.findAllEntity(roleMenuCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(roleMenuList)) {
            for (RoleMenu roleMenu : roleMenuList) {
                if (ESObjectUtils.isNotNull(roleMenu.getLimitable())) {
                    if (roleMenu.getLimitable()) {
                        if (currentTime >= roleMenu.getStartTime() && currentTime <= roleMenu.getEndTime()) {
                            MenuAuthorityResponseDTO menuAuthorityResponseDTOMenu = generateMenuAuthority(currentTime, roleMenu);
                            if (ESObjectUtils.isNotNull(menuAuthorityResponseDTOMenu)) {
                                menuAuthorityResponseDTOList.add(menuAuthorityResponseDTOMenu);
                            }
                        }
                    } else {
                        MenuAuthorityResponseDTO menuAuthorityResponseDTOMenu = generateMenuAuthority(currentTime, roleMenu);
                        if (ESObjectUtils.isNotNull(menuAuthorityResponseDTOMenu)) {
                            menuAuthorityResponseDTOList.add(menuAuthorityResponseDTOMenu);
                        }
                    }
                }
            }
        }
        return menuAuthorityResponseDTOList;
    }

    private MenuAuthorityResponseDTO generateMenuAuthority(Long currentTime, RoleMenu roleMenu) {
        MenuAuthorityResponseDTO menuAuthorityResponseDTOMenu = null;
        Menu menu = menuService.findEntityById(roleMenu.getMenuId());
        if (ESObjectUtils.isNotNull(menu)) {
            menuAuthorityResponseDTOMenu = new MenuAuthorityResponseDTO();
            menuAuthorityResponseDTOMenu.setCode(menu.getCode());
            menuAuthorityResponseDTOMenu.setName(menu.getName());
            menuAuthorityResponseDTOMenu.setRoute(menu.getRoute());
            menuAuthorityResponseDTOMenu.setDescription(menu.getDescription());
            menuAuthorityResponseDTOMenu.setLimitable(roleMenu.getLimitable());
            menuAuthorityResponseDTOMenu.setStartTime(roleMenu.getStartTime());
            menuAuthorityResponseDTOMenu.setEndTime(roleMenu.getEndTime());
            List<OperationAuthorityResponseDTO> operationAuthorityResponseDTOList = new ArrayList<>();
            // 操作
            Criteria<RoleMenuOperation> roleMenuOperationCriteria = new Criteria<>();
            roleMenuOperationCriteria.add(Restrictions.eq("roleId", roleMenu.getRoleId()));
            roleMenuOperationCriteria.add(Restrictions.eq("menuId", roleMenu.getMenuId()));
            List<RoleMenuOperation> roleMenuOperationList = roleMenuOperationService.findAllEntity(roleMenuOperationCriteria, new ArrayList<>());
            if (CollectionUtils.isNotEmpty(roleMenuOperationList)) {
                for (RoleMenuOperation roleMenuOperation : roleMenuOperationList) {
                    if (ESObjectUtils.isNotNull(roleMenuOperation.getLimitable())) {
                        if (roleMenuOperation.getLimitable()) {
                            if (currentTime >= roleMenuOperation.getStartTime() && currentTime <= roleMenuOperation.getEndTime()) {
                                OperationAuthorityResponseDTO operationAuthorityResponseDTO = generateOperationAuthority(roleMenuOperation);
                                if (ESObjectUtils.isNotNull(operationAuthorityResponseDTO)) {
                                    operationAuthorityResponseDTOList.add(operationAuthorityResponseDTO);
                                }
                            }
                        } else {
                            OperationAuthorityResponseDTO operationAuthorityResponseDTO = generateOperationAuthority(roleMenuOperation);
                            if (ESObjectUtils.isNotNull(operationAuthorityResponseDTO)) {
                                operationAuthorityResponseDTOList.add(operationAuthorityResponseDTO);
                            }
                        }
                    }
                }
            }
            menuAuthorityResponseDTOMenu.setOperationList(operationAuthorityResponseDTOList);
        } else {
            logger.error("菜单关系对应菜单找不到：{}", roleMenu);
        }
        return menuAuthorityResponseDTOMenu;
    }

    private OperationAuthorityResponseDTO generateOperationAuthority(RoleMenuOperation roleMenuOperation) {
        OperationAuthorityResponseDTO operationAuthorityResponseDTO = null;
        Operation operation = operationService.findEntityById(roleMenuOperation.getOperationId());
        if (ESObjectUtils.isNotNull(operation)) {
            operationAuthorityResponseDTO = new OperationAuthorityResponseDTO();
            operationAuthorityResponseDTO.setCode(operation.getCode());
            operationAuthorityResponseDTO.setName(operation.getName());
            operationAuthorityResponseDTO.setDescription(operation.getDescription());
            operationAuthorityResponseDTO.setLimitable(roleMenuOperation.getLimitable());
            operationAuthorityResponseDTO.setStartTime(roleMenuOperation.getStartTime());
            operationAuthorityResponseDTO.setEndTime(roleMenuOperation.getEndTime());
        } else {
            logger.error("菜单操作关系对应操作找不到：{}", roleMenuOperation);
        }
        return operationAuthorityResponseDTO;
    }
}
