package com.wondernect.stars.rbac.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 操作事件
 */
public class OperationEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5375530166859492737L;

    private RBACEventType rbacEventType;

    private String operationCode;

    public OperationEvent(Object source, RBACEventType rbacEventType, String operationCode) {
        super(source);
        this.rbacEventType = rbacEventType;
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

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }
}
