package com.wondernect.stars.user.dto.auth.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserLocalAuthResponseDTO
 * Author: chenxun
 * Date: 2019/7/17 9:53
 * Description: 用户本地认证响应对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户本地认证响应对象")
public class UserLocalAuthResponseDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "唯一标识")
    private String userId;

    @JsonProperty("password")
    @ApiModelProperty(notes = "密码")
    private String password;
}
