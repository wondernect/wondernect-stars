package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "应用名称不能为空")
    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("logo")
    @ApiModelProperty(notes = "logo")
    private String logo;

    @JsonProperty("brief")
    @ApiModelProperty(notes = "简介")
    private String brief;

    @JsonProperty("website")
    @ApiModelProperty(notes = "官网链接")
    private String website;

    @NotBlank(message = "应用访问秘钥不能为空")
    @JsonProperty("secret")
    @ApiModelProperty(notes = "访问秘钥")
    private String secret;

    @NotBlank(message = "绑定管理员用户id不能为空")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id")
    private String userId;
}