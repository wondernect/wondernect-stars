package com.wondernect.stars.user.dto.auth.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AuthUserLocalAuthRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description: 本地用户认证认证请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "本地用户认证认证请求对象")
public class AuthUserLocalAuthRequestDTO {

    @NotBlank(message = "用户id不能为空")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @NotBlank(message = "密码不能为空")
    @JsonProperty("password")
    @ApiModelProperty(notes = "密码")
    private String password;
}
