package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色类型事件
 */
public class RoleTypeEvent extends ApplicationEvent {

    private static final long serialVersionUID = -3491771346020804136L;

    private RBACEventType rbacEventType;

    private String roleTypeCode;

    public RoleTypeEvent(Object source, RBACEventType rbacEventType, String roleTypeCode) {
        super(source);
        this.rbacEventType = rbacEventType;
        this.roleTypeCode = roleTypeCode;
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

    public String getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(String roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }
}
