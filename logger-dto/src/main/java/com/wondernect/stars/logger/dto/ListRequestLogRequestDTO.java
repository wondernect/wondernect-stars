package com.wondernect.stars.logger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 日志列表请求DTO
 *
 * @author chenxun 2020-12-17 14:58:51
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "日志列表请求对象")
public class ListRequestLogRequestDTO extends ListRequestDTO {

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

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "操作应用id")
    private String appId;

    @JsonProperty("operation")
    @ApiModelProperty(notes = "操作")
    private String operation;

    @JsonProperty("request_id")
    @ApiModelProperty(notes = "请求id")
    private String requestId;

    @JsonProperty("url")
    @ApiModelProperty(notes = "请求url")
    private String url;
}