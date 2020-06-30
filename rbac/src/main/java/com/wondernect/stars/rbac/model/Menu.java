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
 * FileName: Menu
 * Author: chenxun
 * Date: 2018/11/5 10:26
 * Description: 菜单
 */
@Entity
@Table(
        name = "menu",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "code"),
                @Index(columnList = "parentMenuId")
        }
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "菜单")
public class Menu extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 3413354520318544863L;

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("code")
    @ApiModelProperty(notes = "代码")
    private String code;

    @JsonProperty("route")
    @ApiModelProperty(notes = "路由")
    private String route;

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

    @JsonProperty("parent_menu_id")
    @ApiModelProperty(notes = "父级菜单id")
    private String parentMenuId;

    public Menu() {
    }

    public Menu(String name, String code, String route, String description, String parentMenuId) {
        this.name = name;
        this.code = code;
        this.route = route;
        this.description = description;
        this.editable = true;
        this.deletable = true;
        this.weight = 0;
        this.parentMenuId = parentMenuId;
    }

    public Menu(String name, String code, String route, String description, Boolean editable, Boolean deletable, Integer weight, String parentMenuId) {
        this.name = name;
        this.code = code;
        this.route = route;
        this.description = description;
        this.editable = ESObjectUtils.isNotNull(editable) ? editable : true;
        this.deletable = ESObjectUtils.isNotNull(deletable) ? deletable : true;
        this.weight = weight;
        this.parentMenuId = parentMenuId;
    }
}
