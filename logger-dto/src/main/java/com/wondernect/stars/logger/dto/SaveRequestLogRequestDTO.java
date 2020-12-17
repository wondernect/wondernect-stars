package com.wondernect.stars.logger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 日志请求DTO
 *
 * @author chenxun 2020-12-17 14:58:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "日志请求对象")
public class SaveRequestLogRequestDTO {

    @NotBlank(message = "等级不能为空")
    @JsonProperty("level")
    @ApiModelProperty(notes = "等级")
    private String level;

    @NotBlank(message = "服务不能为空")
    @JsonProperty("service")
    @ApiModelProperty(notes = "服务")
    private String service;

    @NotBlank(message = "模块不能为空")
    @JsonProperty("module")
    @ApiModelProperty(notes = "模块")
    private String module;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "操作用户id")
    private String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "操作用户名")
    private String userName;

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "操作应用id")
    private String appId;

    @JsonProperty("app_name")
    @ApiModelProperty(notes = "操作应用名称")
    private String appName;

    @NotBlank(message = "操作不能为空")
    @JsonProperty("operation")
    @ApiModelProperty(notes = "操作")
    private String operation;

    @NotBlank(message = "日志描述不能为空")
    @JsonProperty("description")
    @ApiModelProperty(notes = "日志描述")
    private String description;

    @JsonProperty("request_id")
    @ApiModelProperty(notes = "请求id")
    private String requestId;

    @JsonProperty("url")
    @ApiModelProperty(notes = "请求url")
    private String url;

    @JsonProperty("method")
    @ApiModelProperty(notes = "请求method")
    private String method;

    @JsonProperty("arg_value")
    @ApiModelProperty(notes = "请求参数")
    private String argValue;

    @JsonProperty("return_value")
    @ApiModelProperty(notes = "返回结果")
    private String returnValue;

    @JsonProperty("run_start_time")
    @ApiModelProperty(notes = "开始运行时间")
    private Long runStartTime;

    @JsonProperty("run_time")
    @ApiModelProperty(notes = "运行时间")
    private Long runTime;

    @JsonProperty("ip")
    @ApiModelProperty(notes = "客户端ip")
    private String ip;

    @JsonProperty("device_platform")
    @ApiModelProperty(notes = "客户端平台")
    private String devicePlatform;

    @JsonProperty("device_description")
    @ApiModelProperty(notes = "客户端描述")
    private String deviceDescription;
}