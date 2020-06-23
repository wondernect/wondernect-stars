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
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: Role
 * Author: chenxun
 * Date: 2018/11/5 10:24
 * Description: 角色
 */
@Entity
@Table(
        name = "role",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "code"),
                @Index(columnList = "roleType")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色")
public class Role extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -420047983890095875L;

    @JsonProperty("code")
    @ApiModelProperty(notes = "代码")
    private String code;

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("description")
    @ApiModelProperty(notes = "描述")
    private String description;

    @JsonProperty("editable")
    @ApiModelProperty(notes = "是否可编辑")
    private Boolean editable;

    @JsonProperty("deletable")
    @ApiModelProperty(notes = "是否可删除")
    private Boolean deletable;

    @JsonProperty("weight")
    @ApiModelProperty(notes = "权重")
    private Integer weight;

    @JsonProperty("role_type")
    @ApiModelProperty(notes = "角色类型")
    private String roleType;

    public Role() {
    }

    public Role(String code, String name, String description, String roleType) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.editable = true;
        this.deletable = true;
        this.weight = 0;
        this.roleType = roleType;
    }

    public Role(String code, String name, String description, Boolean editable, Boolean deletable, Integer weight, String roleType) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.editable = ESObjectUtils.isNotNull(editable) ? editable : true;
        this.deletable = ESObjectUtils.isNotNull(deletable) ? deletable : true;
        this.weight = weight;
        this.roleType = roleType;
    }
}
