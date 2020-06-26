package com.wondernect.stars.session.dto.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CodeRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "临时令牌请求对象")
public class CodeRequestDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

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
