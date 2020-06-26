package com.wondernect.stars.session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: CaptchaSession
 * Author: chenxun
 * Date: 2018/12/27 16:49
 * Description: 验证码会话
 */
@Entity
@Table(name = "captcha_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "验证码")
public class CaptchaSession extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 5831810854848274938L;

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户名")
    private String username;

    @JsonProperty("captcha")
    @ApiModelProperty(notes = "验证码(默认为4位数字)")
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
    @ApiModelProperty(notes = "验证码过期时间(默认1800s)")
    private Long expires;

    @JsonProperty("ip")
    @ApiModelProperty(notes = "客户端ip")
    private String ip;

    @JsonProperty("device_platform")
    @ApiModelProperty(notes = "客户端平台")
    private String devicePlatform;

    @Lob
    @JsonProperty("device_description")
    @ApiModelProperty(notes = "客户端描述")
    private String deviceDescription;
}
