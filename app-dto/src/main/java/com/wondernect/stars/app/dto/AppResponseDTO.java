package com.wondernect.stars.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 应用响应DTO
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "应用响应对象")
public class AppResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("logo")
    @ApiModelProperty(notes = "logo")
    private String logo;

    @JsonProperty("brief")
    @ApiModelProperty(notes = "简介")
    private String brief;

    @JsonProperty("website")
    @ApiModelProperty(notes = "官网链接")
    private String website;

    @JsonProperty("secret")
    @ApiModelProperty(notes = "访问秘钥")
    private String secret;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定管理员用户id")
    private String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(notes = "绑定管理员用户姓名")
    private String userName;
}