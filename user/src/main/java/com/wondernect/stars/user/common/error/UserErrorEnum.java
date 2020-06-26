package com.wondernect.stars.user.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: UserErrorEnum
 * Author: chenxun
 * Date: 2018/12/29 15:24
 * Description:
 */
public enum UserErrorEnum {

    // user
    USER_MOBILE_INVALID("USER_MOBILE_INVALID", "手机号码格式非法"),
    USER_EMAIL_INVALID("USER_EMAIL_INVALID", "邮箱格式非法"),
    USER_USERNAME_HAS_REGIST("USER_USERNAME_HAS_REGIST", "用户名已注册"),
    USER_APP_HAS_REGIST("USER_APP_HAS_REGIST", "第三方用户已注册"),
    USER_NOT_FOUND("USER_NOT_FOUND", "用户信息不存在"),
    USER_DELETE_OWN_ALLOWED("USER_DELETE_OWN_ALLOWED", "用户无法删除自己信息"),
    USER_DELETE_NOT_ALLOWED("USER_DELETE_NOT_ALLOWED", "没有权限删除用户信息"),
    USER_MOBILE_EMAIL_CAN_NOT_ALL_NULL("USER_MOBILE_EMAIL_CAN_NOT_ALL_NULL", "用户手机号码或邮箱不能全部为空"),
    USER_APP_TYPE_INVALID("USER_APP_TYPE_INVALID", "app注册方式非法"),

    // user local auth
    USER_LOCAL_AUTH_HAS_REGIST("USER_LOCAL_AUTH_HAS_REGIST", "用户对应本地认证信息已存在"),
    USER_LOCAL_AUTH_NOT_FOUND("USER_LOCAL_AUTH_NOT_FOUND", "用户对应本地认证信息不存在"),
    USER_LOCAL_AUTH_PASSWORD_FAILED("USER_LOCAL_AUTH_PASSWORD_FAILED", "密码认证失败"),

    // user third auth
    USER_THIRD_AUTH_HAS_REGIST("USER_THIRD_AUTH_HAS_REGIST", "用户对应第三方绑定信息已存在"),
    USER_THIRD_AUTH_NOT_FOUND("USER_THIRD_AUTH_NOT_FOUND", "用户对应第三方绑定信息不存在"),

    ;

    @Getter @Setter
    private String code;

    @Getter @Setter
    private String message;

    UserErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserErrorEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

