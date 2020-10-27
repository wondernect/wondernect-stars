package com.wondernect.stars.user.dto.auth.third;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.stars.user.em.AppType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: AuthUserLocalAuthRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description: 第三方用户认证认证请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "第三方用户认证认证请求对象")
public class AuthUserThirdAuthRequestDTO {

    @NotNull(message = "第三方注册类型不能为空")
    @JsonProperty("app_type")
    @ApiModelProperty(notes = "第三方注册类型")
    private AppType appType;

    @NotBlank(message = "第三方注册用户id不能为空")
    @JsonProperty("app_user_id")
    @ApiModelProperty(notes = "第三方注册用户id")
    private String appUserId;
}
