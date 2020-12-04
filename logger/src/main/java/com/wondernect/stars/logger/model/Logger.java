package com.wondernect.stars.logger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: Logger
 * Author: chenxun
 * Date: 2020/12/2 17:23
 * Description: 日志
 */
@Entity
@Table(
        name = "logger",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
)
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "日志")
public class Logger extends BaseStringModel {

    @JsonProperty("level")
    @ApiModelProperty(notes = "等级")
    private String level;

    @JsonProperty("service")
    @ApiModelProperty(notes = "服务")
    private String service;

    @JsonProperty("module")
    @ApiModelProperty(notes = "模块")
    private String module;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "操作用户id")
    private String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "操作用户名")
    private String userName;

    @JsonProperty("operation")
    @ApiModelProperty(notes = "操作")
    private String operation;

    @JsonProperty("description")
    @ApiModelProperty(notes = "日志描述")
    private String description;

    @JsonProperty("trace_id")
    @ApiModelProperty(notes = "链路跟踪id")
    private String traceId;

}
