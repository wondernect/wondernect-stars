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
 * FileName: RoleMenuOperation
 * Author: chenxun
 * Date: 2019/7/16 14:27
 * Description: 菜单操作
 */
@Entity
@Table(
        name = "role_menu_operation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "roleId"),
                @Index(columnList = "menuId"),
                @Index(columnList = "operationId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色菜单操作")
public class RoleMenuOperation extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -5322119641003855703L;

    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;

    @JsonProperty("menu_id")
    @ApiModelProperty(notes = "菜单id")
    private String menuId;

    @JsonProperty("operation_id")
    @ApiModelProperty(notes = "操作id")
    private String operationId;

    @JsonProperty("limitable")
    @ApiModelProperty(notes = "是否限制可见时间")
    private Boolean limitable;

    @JsonProperty("start_time")
    @ApiModelProperty(notes = "可见开始时间")
    private Long startTime;

    @JsonProperty("end_time")
    @ApiModelProperty(notes = "可见结束时间")
    private Long endTime;

    public RoleMenuOperation() {
    }

    public RoleMenuOperation(String roleId, String menuId, String operationId) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.operationId = operationId;
        this.limitable = false;
        this.startTime = null;
        this.endTime = null;
    }

    public RoleMenuOperation(String roleId, String menuId, String operationId, Boolean limitable, Long startTime, Long endTime) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.operationId = operationId;
        this.limitable = ESObjectUtils.isNotNull(limitable) ? limitable : false;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
