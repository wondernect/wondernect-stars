package com.wondernect.stars.rbac.dto.rolemenuoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: RoleMenuOperationResponseDTO
 * Author: chenxun
 * Date: 2019/7/2 14:22
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色对应菜单对应操作响应对象")
public class RoleMenuOperationResponseDTO {

    @JsonProperty("id")
    @ApiModelProperty(notes = "唯一标识")
    private String id;

    @JsonProperty("code")
    @ApiModelProperty(notes = "操作代码")
    private String code;

    @JsonProperty("name")
    @ApiModelProperty(notes = "操作名称")
    private String name;

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
