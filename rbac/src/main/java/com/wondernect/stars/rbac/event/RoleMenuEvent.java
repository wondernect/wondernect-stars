package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色菜单事件
 */
public class RoleMenuEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6382685223838789318L;

    private RBACEventType rbacEventType;

    private String roleCode;

    private String menuCode;

    public RoleMenuEvent(Object source, RBACEventType rbacEventType, String roleCode, String menuCode) {
        super(source);
        this.rbacEventType = rbacEventType;
        this.roleCode = roleCode;
        this.menuCode = menuCode;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public RBACEventType getRbacEventType() {
        return rbacEventType;
    }

    public void setRbacEventType(RBACEventType rbacEventType) {
        this.rbacEventType = rbacEventType;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
