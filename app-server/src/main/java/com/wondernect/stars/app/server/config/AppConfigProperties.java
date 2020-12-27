package com.wondernect.stars.app.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RBACConfigProperties
 * Author: chenxun
 * Date: 2020-09-20 10:10
 * Description:
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.stars.app")
public class AppConfigProperties implements Serializable {

    private static final long serialVersionUID = 3260656889942519185L;

    private String appId = "UMS"; // UMS应用id

    private String appSecret = "10001"; // UMS应用密钥

    private String userId = "10001"; // UMS应用管理员id

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
