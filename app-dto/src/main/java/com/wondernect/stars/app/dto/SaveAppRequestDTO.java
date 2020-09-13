package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 应用请求DTO
 *
 * @author chenxun 2020-09-13 23:01:59
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用请求对象")
public class SaveAppRequestDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("secret")
    @ApiModelProperty(notes = "访问秘钥")
    private String secret;

    @JsonProperty("logo")
    @ApiModelProperty(notes = "logo")
    private String logo;

    @JsonProperty("brief")
    @ApiModelProperty(notes = "简介")
    private String brief;

    @JsonProperty("website")
    @ApiModelProperty(notes = "官网链接")
    private String website;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id")
    private String userId;
}