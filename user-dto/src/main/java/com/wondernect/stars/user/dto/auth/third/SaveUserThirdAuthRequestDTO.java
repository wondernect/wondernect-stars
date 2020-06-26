package com.wondernect.stars.user.dto.auth.third;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.stars.user.em.AppType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SaveUserThirdAuthRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description: 第三方用户认证创建/更新请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "第三方用户认证创建/更新请求对象")
public class SaveUserThirdAuthRequestDTO {

    @JsonProperty("app_type")
    @ApiModelProperty(notes = "第三方注册类型")
    private AppType appType;

    @NotBlank(message = "第三方注册用户id不能为空")
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
