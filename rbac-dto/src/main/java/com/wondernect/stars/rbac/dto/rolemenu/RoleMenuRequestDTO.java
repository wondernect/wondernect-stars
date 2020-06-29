package com.wondernect.stars.rbac.dto.rolemenu;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: RoleMenuRequestDTO
 * Author: chenxun
 * Date: 2019/8/23 10:43
 * Description: 角色菜单请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色菜单请求对象")
public class RoleMenuRequestDTO {

    @NotBlank(message = "角色代码不能为空")
    @JsonProperty("role_code")
    @ApiModelProperty(notes = "角色代码")
    private String roleCode;

    @NotNull(message = "菜单代码不能为空")
    @JsonProperty("menu_code")
    @ApiModelProperty(notes = "菜单代码")
    private String menuCode;

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
