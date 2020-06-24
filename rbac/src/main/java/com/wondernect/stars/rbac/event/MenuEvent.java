package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 菜单事件
 */
public class MenuEvent extends ApplicationEvent {

    private static final long serialVersionUID = 2871206165866545013L;

    private RBACEventType rbacEventType;

    private String menuCode;

    public MenuEvent(Object source, RBACEventType rbacEventType, String menuCode) {
        super(source);
        this.rbacEventType = rbacEventType;
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

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}
