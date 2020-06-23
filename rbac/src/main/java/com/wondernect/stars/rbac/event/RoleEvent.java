package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色类型事件
 */
public class RoleEvent extends ApplicationEvent {

    private static final long serialVersionUID = 7987832574613396467L;

    private RBACEventType rbacEventType;

    private String roleCode;

    public RoleEvent(Object source, RBACEventType rbacEventType, String roleCode) {
        super(source);
        this.rbacEventType = rbacEventType;
        this.roleCode = roleCode;
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
}
