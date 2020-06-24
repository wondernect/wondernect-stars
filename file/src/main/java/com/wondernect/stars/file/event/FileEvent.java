package com.wondernect.stars.file.event;

import org.springframework.context.ApplicationEvent;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeEvent
 * Author: chenxun
 * Date: 2020-06-23 14:08
 * Description: 角色事件
 */
public class FileEvent extends ApplicationEvent {

    private static final long serialVersionUID = -6142088500364557373L;

    private FileEventType fileEventType;

    private String fileId;

    private String operator;

    public FileEvent(Object source, FileEventType fileEventType, String fileId, String operator) {
        super(source);
        this.fileEventType = fileEventType;
        this.fileId = fileId;
        this.operator = operator;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public FileEventType getFileEventType() {
        return fileEventType;
    }

    public void setFileEventType(FileEventType fileEventType) {
        this.fileEventType = fileEventType;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
