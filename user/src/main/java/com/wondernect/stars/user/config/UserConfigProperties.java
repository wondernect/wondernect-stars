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
@ConfigurationProperties(prefix = "wondernect.stars.user")
public class UserConfigProperties implements Serializable {

    private static final long serialVersionUID = -7644913790725972293L;

    private String username; // 平台超级管理员登录名

    private String name; // 平台超级管理员姓名或昵称

    private String mobile; // 平台超级管理员手机号码

    private String email; // 平台超级管理员邮箱

    private String password; // 平台超级管理员登录密码

    private String passwordEncryptSalt; // 平台用户登录密码加密salt

    private String roleTypeId; // 平台超级管理员角色类型

    private String roleId; // 平台超级管理员角色

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getPasswordEncryptSalt() {
        return passwordEncryptSalt;
    }

    public void setPasswordEncryptSalt(String passwordEncryptSalt) {
        this.passwordEncryptSalt = passwordEncryptSalt;
    }

    public String getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(String roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
