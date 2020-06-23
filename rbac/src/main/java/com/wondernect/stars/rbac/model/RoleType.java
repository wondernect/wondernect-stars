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
 * FileName: RoleType
 * Author: chenxun
 * Date: 2019/7/17 9:46
 * Description: 角色类型
 */
@Entity
@Table(
        name = "role_type",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "code")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "角色类型")
public class RoleType extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -4445644774483992826L;

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

    public RoleType() {
    }

    public RoleType(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.editable = true;
        this.deletable = true;
        this.weight = 0;
    }

    public RoleType(String code, String name, String description, Boolean editable, Boolean deletable, Integer weight) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.editable = ESObjectUtils.isNotNull(editable) ? editable : true;
        this.deletable = ESObjectUtils.isNotNull(deletable) ? deletable : true;
        this.weight = weight;
    }
}
