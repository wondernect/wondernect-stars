package com.wondernect.stars.session.dto.captcha;

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
 * FileName: CaptchaResponseDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "验证码响应对象")
public class CaptchaResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户名")
    private String username;

    @JsonProperty("captcha")
    @ApiModelProperty(notes = "验证码(默认为6位数字)")
    private String captcha;

    @JsonProperty("captcha_image_file_id")
    @ApiModelProperty(notes = "验证码图片id")
    private String captchaImageFileId;

    @JsonProperty("captcha_image_file_path")
    @ApiModelProperty(notes = "验证码图片访问地址")
    private String captchaImageFilePath;

    @JsonProperty("description")
    @ApiModelProperty(notes = "验证码使用描述")
    private String description;

    @JsonProperty("expires")
    @ApiModelProperty(notes = "验证码过期时间(默认60s)")
    private Long expires;

    @JsonProperty("ip")
    @ApiModelProperty(notes = "客户端ip")
    private String ip;

    @JsonProperty("device_platform")
    @ApiModelProperty(notes = "客户端平台")
    private String devicePlatform;

    @JsonProperty("device_description")
    @ApiModelProperty(notes = "客户端描述")
    private String deviceDescription;
}
