package com.wondernect.stars.rbac.dto.rolemenuoperation;

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
 * FileName: RolePrivilegeRequestDTO
 * Author: chenxun
 * Date: 2019/8/23 10:43
 * Description: 角色菜单操作请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色菜单操作请求对象")
public class RoleMenuOperationRequestDTO {

    @NotBlank(message = "角色id不能为空")
    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;

    @NotBlank(message = "菜单id不能为空")
    @JsonProperty("menu_id")
    @ApiModelProperty(notes = "菜单id")
    private String menuId;

    @NotNull(message = "操作id不能为空")
    @JsonProperty("operation_id")
    @ApiModelProperty(notes = "操作id")
    private String operationId;

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
