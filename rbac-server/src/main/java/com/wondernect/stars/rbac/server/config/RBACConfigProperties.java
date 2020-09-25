package com.wondernect.stars.rbac.server.config;

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
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true, encoding = "UTF-8")
@ConfigurationProperties(prefix = "wondernect.stars.rbac")
public class RBACConfigProperties implements Serializable {

    private static final long serialVersionUID = 3260656889942519185L;

    private String rootMenuId = "ROOT_MENU"; // 根节点菜单id

    private String umsAdminRoleTypeId = "UMS_ADMIN_TYPE"; // 应用管理员角色类型id

    private String umsAdminRoleId = "UMS_ADMIN"; // 应用管理员角色id

    private String umsUserRoleTypeId = "UMS_USER_TYPE"; // 应用注册用户角色类型id

    private String umsUserRoleId = "UMS_USER"; // 应用注册用户角色id

    private String umsAppId = "UMS"; // 应用id

    private String umsAppUserId = "10001"; // 管理员id

    public String getRootMenuId() {
        return rootMenuId;
    }

    public void setRootMenuId(String rootMenuId) {
        this.rootMenuId = rootMenuId;
    }

    public String getUmsAdminRoleTypeId() {
        return umsAdminRoleTypeId;
    }

    public void setUmsAdminRoleTypeId(String umsAdminRoleTypeId) {
        this.umsAdminRoleTypeId = umsAdminRoleTypeId;
    }

    public String getUmsAdminRoleId() {
        return umsAdminRoleId;
    }

    public void setUmsAdminRoleId(String umsAdminRoleId) {
        this.umsAdminRoleId = umsAdminRoleId;
    }

    public String getUmsUserRoleTypeId() {
        return umsUserRoleTypeId;
    }

    public void setUmsUserRoleTypeId(String umsUserRoleTypeId) {
        this.umsUserRoleTypeId = umsUserRoleTypeId;
    }

    public String getUmsUserRoleId() {
        return umsUserRoleId;
    }

    public void setUmsUserRoleId(String umsUserRoleId) {
        this.umsUserRoleId = umsUserRoleId;
    }

    public String getUmsAppId() {
        return umsAppId;
    }

    public void setUmsAppId(String umsAppId) {
        this.umsAppId = umsAppId;
    }

    public String getUmsAppUserId() {
        return umsAppUserId;
    }

    public void setUmsAppUserId(String umsAppUserId) {
        this.umsAppUserId = umsAppUserId;
    }
}
