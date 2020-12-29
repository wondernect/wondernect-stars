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
@ApiModel(value = "应用认证请求对象")
public class AuthAppRequestDTO {

    @NotBlank(message = "访问密钥不能为空")
    @JsonProperty("secret")
    @ApiModelProperty(notes = "加密后访问密钥")
    private String secret;

    @NotBlank(message = "绑定用户id不能为空")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定用户id")
    private String userId;
}