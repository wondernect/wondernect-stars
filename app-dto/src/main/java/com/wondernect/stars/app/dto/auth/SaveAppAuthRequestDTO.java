package com.wondernect.stars.app.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 应用访问认证请求DTO
 *
 * @author chenxun 2020-12-29 16:28:22
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用访问认证请求对象")
public class SaveAppAuthRequestDTO {

    @NotBlank(message = "应用id不能为空")
    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用id")
    private String appId;

    @NotBlank(message = "应用访问密钥不能为空")
    @JsonProperty("secret")
    @ApiModelProperty(notes = "应用访问密钥")
    private String secret;

    @NotBlank(message = "应用访问密钥绑定用户id不能为空")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "应用访问密钥绑定用户id")
    private String userId;
}