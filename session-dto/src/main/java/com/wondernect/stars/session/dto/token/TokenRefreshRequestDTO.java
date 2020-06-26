package com.wondernect.stars.session.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: TokenRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "永久令牌刷新请求对象")
public class TokenRefreshRequestDTO {

    @NotBlank(message = "令牌不能为空")
    @JsonProperty("token")
    @ApiModelProperty(notes = "令牌")
    private String token;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @JsonProperty("device_identifier")
    @ApiModelProperty(notes = "客户端唯一标识(移动端可使用该标识进行消息推送)")
    private String deviceIdentifier;

    @JsonProperty("description")
    @ApiModelProperty(notes = "会话使用描述")
    private String description;

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
