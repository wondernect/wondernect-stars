package com.wondernect.stars.rbac.config;

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
@ConfigurationProperties(prefix = "wondernect.stars.rbac")
public class RBACConfigProperties implements Serializable {

    private static final long serialVersionUID = -7644913790725972293L;

    private String roleTypeId; // 平台超级管理员角色类型id

    private String roleTypeName; // 平台超级管理员角色类型名称

    private String roleTypeDesc; // 平台超级管理员角色类型描述

    private String roleId; // 平台超级管理员角色id

    private String roleName; // 平台超级管理员角色名称

    private String roleDesc; // 平台超级管理员角色描述

    private String menuId; // 平台菜单id

    private String menuName; // 平台菜单名称

    private String menuCode; // 平台菜单代码

    private String menuRoute; // 平台菜单路由

    private String menuDesc; // 平台菜单描述

    public String getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(String roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public String getRoleTypeName() {
        return roleTypeName;
    }

    public void setRoleTypeName(String roleTypeName) {
        this.roleTypeName = ESStringUtils.transformISOToUTF_8(roleTypeName);
    }

    public String getRoleTypeDesc() {
        return roleTypeDesc;
    }

    public void setRoleTypeDesc(String roleTypeDesc) {
        this.roleTypeDesc = ESStringUtils.transformISOToUTF_8(roleTypeDesc);
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = ESStringUtils.transformISOToUTF_8(roleName);
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = ESStringUtils.transformISOToUTF_8(roleDesc);
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = ESStringUtils.transformISOToUTF_8(menuId);
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = ESStringUtils.transformISOToUTF_8(menuName);
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = ESStringUtils.transformISOToUTF_8(menuCode);
    }

    public String getMenuRoute() {
        return menuRoute;
    }

    public void setMenuRoute(String menuRoute) {
        this.menuRoute = ESStringUtils.transformISOToUTF_8(menuRoute);
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = ESStringUtils.transformISOToUTF_8(menuDesc);
    }
}
