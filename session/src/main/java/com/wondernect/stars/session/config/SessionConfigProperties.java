package com.wondernect.stars.session.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SessionConfigProperties
 * Author: chenxun
 * Date: 2019/3/18 9:20
 * Description: session config properties
 */
@Component
@Primary
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
@ConfigurationProperties(prefix = "wondernect.stars.session")
public class SessionConfigProperties implements Serializable {

    private static final long serialVersionUID = 7429567510622613606L;

    private int captchaType = 0; // 验证码类型: 0-数字; 1-字符;

    private int captchaLength = 4; // 验证码长度

    private Long captchaExpires = 1800L; // 验证码过期时间 30分钟

    private Long codeExpires = 7200L; // 临时访问码过期时间 2小时

    public int getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(int captchaType) {
        this.captchaType = captchaType;
    }

    public int getCaptchaLength() {
        return captchaLength;
    }

    public void setCaptchaLength(int captchaLength) {
        this.captchaLength = captchaLength;
    }

    public Long getCaptchaExpires() {
        return captchaExpires;
    }

    public void setCaptchaExpires(Long captchaExpires) {
        this.captchaExpires = captchaExpires;
    }

    public Long getCodeExpires() {
        return codeExpires;
    }

    public void setCodeExpires(Long codeExpires) {
        this.codeExpires = codeExpires;
    }
}
