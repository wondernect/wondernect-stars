package com.wondernect.stars.rbac.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: PrivilegeResponseDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "菜单响应对象")
public class MenuResponseDTO extends BaseStringResponseDTO {

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

    @JsonProperty("parent_menu_name")
    @ApiModelProperty(notes = "父级菜单名称")
    private String parentMenuName;

    @JsonProperty("parent_menu_code")
    @ApiModelProperty(notes = "父级菜单代码")
    private String parentMenuCode;

    @JsonProperty("parent_menu_route")
    @ApiModelProperty(notes = "父级菜单路由")
    private String parentMenuRoute;
}
