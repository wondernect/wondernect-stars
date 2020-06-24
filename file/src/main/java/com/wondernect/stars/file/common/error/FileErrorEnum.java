package com.wondernect.stars.file.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: FileErrorEnum
 * Author: chenxun
 * Date: 2018/12/29 15:24
 * Description:
 */
public enum FileErrorEnum {

    // file service
    FILE_UPLOAD_FAILED("FILE_UPLOAD_FAILED", "文件上传失败"),
    FILE_TYPE_IS_NULL("FILE_TYPE_IS_NULL", "上传文件格式为空"),
    FILE_TYPE_INVALID("FILE_TYPE_INVALID", "上传文件格式不支持"),
    FILE_HAS_DELETED("FILE_HAS_DELETED", "文件已被删除"),
    FILE_NOT_FOUND("FILE_NOT_FOUND", "文件不存在"),
    FILE_DELETE_FAILED("FILE_DELETE_FAILED", "删除文件失败"),
    FILE_DELETE_NOT_ALLOW("FILE_DELETE_NOT_ALLOW", "没有删除文件的权限"),

    ;

    @Getter @Setter
    private String code;

    @Getter @Setter
    private String message;

    FileErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "FileErrorEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

