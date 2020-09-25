package com.wondernect.stars.rbac.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SavePrivilegeRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "菜单创建or更新请求对象")
public class SaveMenuRequestDTO {

    @NotBlank(message = "菜单名称不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @NotBlank(message = "菜单代码不能为空")
    @JsonProperty("code")
    @ApiModelProperty(notes = "菜单代码")
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
}
