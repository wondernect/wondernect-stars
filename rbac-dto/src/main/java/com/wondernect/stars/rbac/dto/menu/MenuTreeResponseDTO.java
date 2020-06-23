package com.wondernect.stars.rbac.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ZoneResponseDTO
 * Author: chenxun
 * Date: 2019/7/2 14:22
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "菜单树形响应对象")
public class MenuTreeResponseDTO {

    @JsonProperty("id")
    @ApiModelProperty(notes = "唯一标识")
    private String id;

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

    @JsonProperty("parent_menu_code")
    @ApiModelProperty(notes = "父级菜单code")
    private String parentMenuCode;

    @JsonProperty("parent_menu_name")
    @ApiModelProperty(notes = "父级菜单名称")
    private String parentMenuName;

    @JsonProperty("child_list")
    @ApiModelProperty(notes = "子节点列表")
    private List<MenuTreeResponseDTO> childList;
}
