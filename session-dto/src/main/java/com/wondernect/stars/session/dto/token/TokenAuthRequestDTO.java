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
 * FileName: TokenAuthRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:39
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "永久令牌验证请求对象")
public class TokenAuthRequestDTO {

    @NotBlank(message = "令牌不能为空")
    @JsonProperty("token")
    @ApiModelProperty(notes = "令牌")
    private String token;
}
