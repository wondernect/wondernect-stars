package com.wondernect.stars.rbac.server;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.rbac.manager.MenuManager;
import com.wondernect.stars.rbac.manager.RoleManager;
import com.wondernect.stars.rbac.manager.RoleTypeManager;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.model.Role;
import com.wondernect.stars.rbac.model.RoleType;
import com.wondernect.stars.rbac.server.config.RBACConfigProperties;
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
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private RoleTypeManager roleTypeManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private MenuManager menuManager;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                // 根节点菜单
                if (ESObjectUtils.isNull(menuManager.findById(rbacConfigProperties.getRootMenuId()))) {
                    Menu menu = new Menu(
                            "根节点菜单",
                            rbacConfigProperties.getRootMenuId(),
                            "",
                            "根节点菜单",
                            true,
                            false,
                            0,
                            "-1"
                    );
                    menu.setId(rbacConfigProperties.getRootMenuId());
                    menuManager.save(menu);
                }
                // 设置初始化数据创建app和user
                wondernectCommonContext.getAuthorizeData().setAppId(rbacConfigProperties.getUmsAppId());
                wondernectCommonContext.getAuthorizeData().setUserId(rbacConfigProperties.getUmsAppUserId());
                // 管理员
                if (ESObjectUtils.isNull(roleTypeManager.findById(rbacConfigProperties.getUmsAdminRoleTypeId()))) {
                    RoleType roleType = new RoleType(
                            "UMS管理员角色类型",
                            "UMS管理员角色类型",
                            true,
                            false,
                            0
                    );
                    roleType.setId(rbacConfigProperties.getUmsAdminRoleTypeId());
                    roleTypeManager.save(roleType);
                }
                if (ESObjectUtils.isNull(roleManager.findById(rbacConfigProperties.getUmsAdminRoleId()))) {
                    Role role = new Role(
                            "UMS管理员角色",
                            "UMS管理员角色",
                            true,
                            false,
                            0,
                            rbacConfigProperties.getUmsAdminRoleTypeId()
                    );
                    role.setId(rbacConfigProperties.getUmsAdminRoleId());
                    roleManager.save(role);
                }
                // 注册用户
                if (ESObjectUtils.isNull(roleTypeManager.findById(rbacConfigProperties.getUmsUserRoleTypeId()))) {
                    RoleType roleType = new RoleType(
                            "UMS注册用户角色类型",
                            "UMS注册用户角色类型",
                            true,
                            false,
                            0
                    );
                    roleType.setId(rbacConfigProperties.getUmsUserRoleTypeId());
                    roleTypeManager.save(roleType);
                }
                if (ESObjectUtils.isNull(roleManager.findById(rbacConfigProperties.getUmsUserRoleId()))) {
                    Role role = new Role(
                            "UMS注册用户角色",
                            "UMS注册用户角色",
                            true,
                            false,
                            0,
                            rbacConfigProperties.getUmsUserRoleTypeId()
                    );
                    role.setId(rbacConfigProperties.getUmsUserRoleId());
                    roleManager.save(role);
                }
                break;
            }
            default:
                break;
        }
    }
}
