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
                @Index(columnList = "roleCode"),
                @Index(columnList = "menuCode"),
                @Index(columnList = "operationCode")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色菜单操作")
public class RoleMenuOperation extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -5322119641003855703L;

    @JsonProperty("role_code")
    @ApiModelProperty(notes = "角色代码")
    private String roleCode;

    @JsonProperty("menu_code")
    @ApiModelProperty(notes = "菜单代码")
    private String menuCode;

    @JsonProperty("operation_code")
    @ApiModelProperty(notes = "操作代码")
    private String operationCode;

    @JsonProperty("limitable")
    @ApiModelProperty(notes = "是否限制可见时间")
    private Boolean limitable;

    @JsonProperty("start_time")
    @ApiModelProperty(notes = "可见开始时间")
    private Long startTime;

    @JsonProperty("endTime")
    @ApiModelProperty(notes = "可见结束时间")
    private Long endTime;

    public RoleMenuOperation() {
    }

    public RoleMenuOperation(String roleCode, String menuCode, String operationCode) {
        this.roleCode = roleCode;
        this.menuCode = menuCode;
        this.operationCode = operationCode;
        this.limitable = false;
        this.startTime = null;
        this.endTime = null;
    }

    public RoleMenuOperation(String roleCode, String menuCode, String operationCode, Boolean limitable, Long startTime, Long endTime) {
        this.roleCode = roleCode;
        this.menuCode = menuCode;
        this.operationCode = operationCode;
        this.limitable = ESObjectUtils.isNotNull(limitable) ? limitable : false;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
