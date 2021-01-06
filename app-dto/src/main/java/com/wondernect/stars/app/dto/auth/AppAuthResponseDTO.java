package com.wondernect.stars.app.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 应用访问认证响应DTO
 *
 * @author chenxun 2020-12-29 16:28:22
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用访问认证响应对象")
public class AppAuthResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用id")
    private String appId;

    @JsonProperty("name")
    @ApiModelProperty(notes = "应用名称")
    private String name;

    @JsonProperty("logo")
    @ApiModelProperty(notes = "应用logo")
    private String logo;

    @JsonProperty("brief")
    @ApiModelProperty(notes = "应用简介")
    private String brief;

    @JsonProperty("website")
    @ApiModelProperty(notes = "应用官网链接")
    private String website;

    @JsonProperty("secret")
    @ApiModelProperty(notes = "应用访问密钥")
    private String secret;

    @JsonProperty("access_type")
    @ApiModelProperty(notes = "应用访问类型(1-只读；2-读写；)")
    private Integer accessType;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "应用访问密钥绑定用户id")
    private String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "应用访问密钥绑定用户姓名")
    private String userName;
}