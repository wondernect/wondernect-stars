package com.wondernect.stars.rbac.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: RoleMenu
 * Author: chenxun
 * Date: 2019/7/16 9:20
 * Description: 权限菜单
 */
@Entity
@Table(
        name = "role_menu",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "roleId"),
                @Index(columnList = "menuId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色菜单")
public class RoleMenu extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -4696775970734331693L;

    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;

    @JsonProperty("menu_id")
    @ApiModelProperty(notes = "菜单id")
    private String menuId;

    @JsonProperty("limitable")
    @ApiModelProperty(notes = "是否限制可见时间")
    private Boolean limitable;

    @JsonProperty("start_time")
    @ApiModelProperty(notes = "可见开始时间")
    private Long startTime;

    @JsonProperty("end_time")
    @ApiModelProperty(notes = "可见结束时间")
    private Long endTime;

    public RoleMenu() {
    }

    public RoleMenu(String roleId, String menuId) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.limitable = false;
        this.startTime = null;
        this.endTime = null;
    }

    public RoleMenu(String roleId, String menuId, Boolean limitable, Long startTime, Long endTime) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.limitable = ESObjectUtils.isNotNull(limitable) ? limitable : false;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
