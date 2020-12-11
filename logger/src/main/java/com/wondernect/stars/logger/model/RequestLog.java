package com.wondernect.stars.logger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: Logger
 * Author: chenxun
 * Date: 2020/12/2 17:23
 * Description: 日志
 */
@Entity
@Table(
        name = "request_log",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "日志")
public class RequestLog extends BaseStringModel {

    @Column(columnDefinition = "not null")
    @JsonProperty("level")
    @ApiModelProperty(notes = "等级")
    private String level;

    @Column(columnDefinition = "not null")
    @JsonProperty("service")
    @ApiModelProperty(notes = "服务")
    private String service;

    @Column(columnDefinition = "not null")
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

    @Column(columnDefinition = "not null")
    @JsonProperty("operation")
    @ApiModelProperty(notes = "操作")
    private String operation;

    @Column(columnDefinition = "not null")
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

    @Lob
    @JsonProperty("arg_value")
    @ApiModelProperty(notes = "请求参数")
    private String argValue;

    @Lob
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

    @Lob
    @JsonProperty("device_description")
    @ApiModelProperty(notes = "客户端描述")
    private String deviceDescription;
}
