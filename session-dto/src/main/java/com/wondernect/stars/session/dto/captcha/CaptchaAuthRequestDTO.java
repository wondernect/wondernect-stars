package com.wondernect.stars.session.dto.captcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CaptchaRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "验证码验证请求对象")
public class CaptchaAuthRequestDTO {

    @NotBlank(message = "验证码会话id不能为空")
    @JsonProperty("captcha_session_id")
    @ApiModelProperty(notes = "验证码会话id")
    private String captchaSessionId;

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户名")
    private String username;

    @NotBlank(message = "验证码不能为空")
    @JsonProperty("captcha")
    @ApiModelProperty(notes = "验证码(默认为4位数字)")
    private String captcha;
}
