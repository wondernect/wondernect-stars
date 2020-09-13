package com.wondernect.stars.app.config;

import com.wondernect.elements.common.utils.ESStringUtils;
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
@ConfigurationProperties(prefix = "wondernect.stars.app")
public class AppConfigProperties implements Serializable {

    private static final long serialVersionUID = -6089605811734536869L;

    private String appId; // app初始化id

    private String appName; // app初始化name

    private String secret; // app初始化secret

    private String logo; // app初始化logo

    private String brief; // app初始化简介

    private String website; // app初始化链接地址

    private String userId; // app初始化绑定管理用户id

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = ESStringUtils.transformISOToUTF_8(appName);
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = ESStringUtils.transformISOToUTF_8(brief);
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
