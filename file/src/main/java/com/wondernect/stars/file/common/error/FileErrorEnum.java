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
    FILE_UPLOAD_FAILED("FILE_UPLOAD_FAILED", "文件上传失败,请稍后重试"),
    FILE_TYPE_IS_NULL("FILE_TYPE_IS_NULL", "上传文件格式为空,请检查后重试"),
    FILE_TYPE_INVALID("FILE_TYPE_INVALID", "上传文件格式不支持,请检查后重试"),
    FILE_NOT_FOUND("FILE_NOT_FOUND", "文件不存在,请检查后重试"),
    FILE_DELETE_FAILED("FILE_DELETE_FAILED", "删除文件失败,请检查后重试"),
    FILE_DELETE_NOT_ALLOW("FILE_DELETE_NOT_ALLOW", "没有删除文件的权限,请检查后重试"),

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

