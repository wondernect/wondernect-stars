package com.wondernect.stars.user.config;

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
@ConfigurationProperties(prefix = "wondernect.services.user")
public class UserConfigProperties implements Serializable {

    private static final long serialVersionUID = -7644913790725972293L;

    private String name; // 平台超级管理员姓名

    private String mobile; // 平台超级管理员手机号码

    private String email; // 平台超级管理员邮箱

    private String password; // 平台超级管理员密码

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ESStringUtils.transformISOToUTF_8(name);
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
