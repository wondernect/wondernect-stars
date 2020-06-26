package com.wondernect.stars.user.dto.auth.local;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.i18n.validator.ESPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SaveUserLocalAuthRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description: 本地用户认证创建/更新请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "本地用户认证创建/更新请求对象")
public class SaveUserLocalAuthRequestDTO {

    @ESPassword
    @JsonProperty("password")
    @ApiModelProperty(notes = "密码")
    private String password;
}
