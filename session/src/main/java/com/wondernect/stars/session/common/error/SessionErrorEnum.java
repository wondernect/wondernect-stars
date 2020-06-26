package com.wondernect.stars.session.common.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SessionErrorEnum
 * Author: chenxun
 * Date: 2018/12/29 15:24
 * Description:
 */
public enum SessionErrorEnum {

    // captcha session
    CAPTCHA_SESSION_NOT_FOUND("CAPTCHA_SESSION_NOT_FOUND", "验证码不存在,请检查后请求"),
    CAPTCHA_SESSION_CAPTCHA_INVALID("CAPTCHA_SESSION_CAPTCHA_INVALID", "验证码错误,请检查后重试"),
    CAPTCHA_SESSION_CAPTCHA_EXPIRED("CAPTCHA_SESSION_CAPTCHA_EXPIRED", "验证码已过期,请刷新后重试"),
    CAPTCHA_SESSION_USERNAME_INVALID("CAPTCHA_SESSION_USERNAME_INVALID", "验证码对应手机或邮箱有误,请检查后重试"),

    // code session
    CODE_SESSION_NOT_FOUND("CODE_SESSION_NOT_FOUND", "临时访问码已过期,请重新请求"),
    CODE_SESSION_USER_INVALID("CODE_SESSION_USER_INVALID", "您没有访问码删除权限"),

    // token session
    TOKEN_SESSION_NOT_FOUND("TOKEN_SESSION_NOT_FOUND", "令牌已过期,请重新请求"),
    TOKEN_SESSION_USER_INVALID("TOKEN_SESSION_USER_INVALID", "您没有令牌删除权限"),

    ;

    @Getter @Setter
    private String code;

    @Getter @Setter
    private String message;

    SessionErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "SessionErrorEnum{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

