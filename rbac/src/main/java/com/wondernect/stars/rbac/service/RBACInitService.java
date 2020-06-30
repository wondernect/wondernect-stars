package com.wondernect.stars.rbac.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.stars.rbac.config.RBACConfigProperties;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.RoleMenuManager;
import com.wondernect.stars.rbac.manager.RoleTypeManager;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.RoleMenu;
import com.wondernect.stars.rbac.model.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitService
 * Author: chenxun
 * Date: 2020-02-28 14:31
 * Description: 初始化服务
 */
@Service
public class RBACInitService implements ApplicationListener<WondernectBootEvent> {

    @Autowired
    private RBACConfigProperties rbacConfigProperties;

    @Autowired
    private RoleTypeManager roleTypeManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private MenuManager menuManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                if (ESStringUtils.isNotBlank(rbacConfigProperties.getRoleTypeId()) &&
                        ESObjectUtils.isNull(roleTypeManager.findById(rbacConfigProperties.getRoleTypeId()))) {
                    RoleType roleType = new RoleType(
                            rbacConfigProperties.getRoleTypeName(),
                            rbacConfigProperties.getRoleTypeDesc(),
                            false,
                            false,
                            0
                    );
                    roleType.setId(rbacConfigProperties.getRoleTypeId());
                    roleTypeManager.save(roleType);
                }

                if (ESStringUtils.isNotBlank(rbacConfigProperties.getRoleId()) &&
                        ESObjectUtils.isNull(roleManager.findById(rbacConfigProperties.getRoleId()))) {
                    Role role = new Role(
                            rbacConfigProperties.getRoleName(),
                            rbacConfigProperties.getRoleDesc(),
                            false,
                            false,
                            0,
                            rbacConfigProperties.getRoleTypeId()
                    );
                    role.setId(rbacConfigProperties.getRoleId());
                    roleManager.save(role);
                }

                if (ESStringUtils.isNotBlank(rbacConfigProperties.getMenuId()) &&
                        ESObjectUtils.isNull(menuManager.findByCode(rbacConfigProperties.getMenuId()))) {
                    Menu menu = new Menu(
                            rbacConfigProperties.getMenuName(),
                            rbacConfigProperties.getMenuCode(),
                            rbacConfigProperties.getMenuRoute(),
                            rbacConfigProperties.getMenuDesc(),
                            false,
                            false,
                            0,
                            "-1"
                    );
                    menu.setId(rbacConfigProperties.getMenuId());
                    menuManager.save(menu);
                }

                if (ESStringUtils.isNotBlank(rbacConfigProperties.getRoleId()) &&
                        ESStringUtils.isNotBlank(rbacConfigProperties.getMenuId()) &&
                        ESObjectUtils.isNull(roleMenuManager.findByRoleIdAndMenuId(rbacConfigProperties.getRoleId(), rbacConfigProperties.getMenuId()))) {
                    roleMenuManager.save(
                            new RoleMenu(
                                    rbacConfigProperties.getRoleId(),
                                    rbacConfigProperties.getMenuId(),
                                    false,
                                    null,
                                    null
                            )
                    );
                }
                break;
            }
            default:
                break;
        }
    }
}
