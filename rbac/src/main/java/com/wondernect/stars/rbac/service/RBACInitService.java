package com.wondernect.stars.rbac.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
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
                if (ESObjectUtils.isNull(roleTypeManager.findByCode(rbacConfigProperties.getRoleTypeCode()))) {
                    roleTypeManager.save(
                            new RoleType(
                                    rbacConfigProperties.getRoleTypeCode(),
                                    rbacConfigProperties.getRoleTypeName(),
                                    rbacConfigProperties.getRoleTypeDesc(),
                                    false,
                                    false,
                                    0
                            )
                    );
                }

                if (ESObjectUtils.isNull(roleManager.findByCode(rbacConfigProperties.getRoleCode()))) {
                    roleManager.save(
                            new Role(
                                    rbacConfigProperties.getRoleCode(),
                                    rbacConfigProperties.getRoleName(),
                                    rbacConfigProperties.getRoleDesc(),
                                    false,
                                    false,
                                    0,
                                    rbacConfigProperties.getRoleTypeCode()
                            )
                    );
                }

                if (ESObjectUtils.isNull(menuManager.findByCode(rbacConfigProperties.getMenuCode()))) {
                    menuManager.save(
                            new Menu(
                                    rbacConfigProperties.getMenuCode(),
                                    rbacConfigProperties.getMenuName(),
                                    rbacConfigProperties.getMenuDesc(),
                                    false,
                                    false,
                                    0,
                                    "-1"
                            )
                    );
                }

                if (ESObjectUtils.isNull(roleMenuManager.findByRoleCodeAndMenuCode(rbacConfigProperties.getRoleCode(), rbacConfigProperties.getMenuCode()))) {
                    roleMenuManager.save(
                            new RoleMenu(
                                    rbacConfigProperties.getRoleCode(),
                                    rbacConfigProperties.getMenuCode(),
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
