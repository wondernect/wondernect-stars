package com.wondernect.stars.rbac.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuResponseDTO
 * Author: chenxun
 * Date: 2020-06-29 11:28
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色对应菜单响应对象")
public class RoleMenuResponseDTO {

    @JsonProperty("id")
    @ApiModelProperty(notes = "菜单唯一标识")
    private String id;

    @JsonProperty("name")
    @ApiModelProperty(notes = "菜单名称")
    private String name;

    @JsonProperty("code")
    @ApiModelProperty(notes = "菜单代码")
    private String code;

    @JsonProperty("route")
    @ApiModelProperty(notes = "菜单路由")
    private String route;

    @JsonProperty("visible")
    @ApiModelProperty(notes = "是否可见")
    private Boolean visible;

    @JsonProperty("limitable")
    @ApiModelProperty(notes = "是否限制可见时间")
    private Boolean limitable;

    @JsonProperty("start_time")
    @ApiModelProperty(notes = "可见开始时间")
    private Long startTime;

    @JsonProperty("end_time")
    @ApiModelProperty(notes = "可见结束时间")
    private Long endTime;
}
