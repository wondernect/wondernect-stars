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
 * FileName: Operation
 * Author: chenxun
 * Date: 2019/7/16 10:34
 * Description: 操作
 */
@Entity
@Table(
        name = "operation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "code"),
                @Index(columnList = "menuId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "操作")
public class Operation extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -1936002767036011512L;

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("code")
    @ApiModelProperty(notes = "代码")
    private String code;

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

    @JsonProperty("menu_id")
    @ApiModelProperty(notes = "所属菜单id")
    private String menuId;

    public Operation() {
    }

    public Operation(String code, String name, String description, String menuId) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.editable = true;
        this.deletable = true;
        this.weight = 0;
        this.menuId = menuId;
    }

    public Operation(String code, String name, String description, Boolean editable, Boolean deletable, Integer weight, String menuId) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.editable = ESObjectUtils.isNotNull(editable) ? editable : true;
        this.deletable = ESObjectUtils.isNotNull(deletable) ? deletable : true;
        this.weight = weight;
        this.menuId = menuId;
    }
}
