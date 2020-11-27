package com.wondernect.stars.mail.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.response.BaseStringResponseDTO;
import com.wondernect.stars.mail.em.MailType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 邮箱服务器响应DTO
 *
 * @author 王威 2020-11-23 15:55:46
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮箱服务器响应对象")
public class MailServerResponseDTO extends BaseStringResponseDTO {

    @JsonProperty("type")
    @ApiModelProperty(notes = "邮箱类型")
    private MailType type;

    @JsonProperty("username")
    @ApiModelProperty(notes = "邮箱地址")
    private String username;

    @JsonProperty("password")
    @ApiModelProperty(notes = "授权码/密码")
    private String password;

    @JsonProperty("host")
    @ApiModelProperty(notes = "服务器地址")
    private String host;

    @JsonProperty("port")
    @ApiModelProperty(notes = "端口")
    private int port;
}