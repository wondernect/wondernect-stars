package com.wondernect.stars.user.dto.auth.third;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserThirdAuthResponseDTO
 * Author: chenxun
 * Date: 2019/7/17 9:53
 * Description: 用户第三方认证响应对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户第三方认证响应对象")
public class UserThirdAuthResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "唯一标识")
    private String userId;

    @JsonProperty("app_type")
    @ApiModelProperty(notes = "第三方注册类型")
    private String appType;

    @JsonProperty("app_user_id")
    @ApiModelProperty(notes = "第三方注册用户id")
    private String appUserId;

    @JsonProperty("app_user_name")
    @ApiModelProperty(notes = "第三方注册用户name")
    private String appUserName;

    @JsonProperty("app_user_avatar")
    @ApiModelProperty(notes = "第三方注册用户头像")
    private String appUserAvatar;
}
