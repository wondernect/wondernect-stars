package com.wondernect.stars.session.dto.code;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseCodeResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CodeResponseDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "临时令牌响应对象")
public class CodeResponseDTO extends BaseCodeResponseDTO {

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户")
    private String userId;

    @JsonProperty("description")
    @ApiModelProperty(notes = "令牌使用描述")
    private String description;

    @JsonProperty("expires")
    @ApiModelProperty(notes = "令牌过期时间(默认7200s)")
    private Long expires;

    @JsonProperty("create_time")
    @ApiModelProperty(notes = "创建时间")
    private Long createTime;

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
