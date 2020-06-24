package com.wondernect.stars.rbac.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
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
                if (ESObjectUtils.isNull(roleTypeManager.findByCode("super"))) {
                    roleTypeManager.save(new RoleType("super", "超管", "超管", false, false, 0));
                }

                if (ESObjectUtils.isNull(roleManager.findByCode("super_default"))) {
                    roleManager.save(new Role("super_default", "超管默认角色", "超管默认角色", false, false, 0, "super"));
                }

                if (ESObjectUtils.isNull(menuManager.findByCode("0"))) {
                    menuManager.save(new Menu("0", "菜单管理系统", "菜单管理系统", false, false, 0, "-1"));
                }

                if (ESObjectUtils.isNull(roleMenuManager.findByRoleCodeAndMenuCode("super_default", "0"))) {
                    roleMenuManager.save(new RoleMenu("super_default", "0", false, null, null));
                }
                break;
            }
            default:
                break;
        }
    }
}
