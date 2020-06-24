package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色菜单事件
 */
public class RoleMenuOperationEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6961131970409741238L;

    private RBACEventType rbacEventType;

    private String roleCode;

    private String menuCode;

    private String operationCode;

    public RoleMenuOperationEvent(Object source, RBACEventType rbacEventType, String roleCode, String menuCode, String operationCode) {
        super(source);
        this.rbacEventType = rbacEventType;
        this.roleCode = roleCode;
        this.menuCode = menuCode;
        this.operationCode = operationCode;
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

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }
}
