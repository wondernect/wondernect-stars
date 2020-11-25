package com.wondernect.stars.mail.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.stars.mail.em.MailType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮箱服务器请求DTO
 *
 * @author 王威 2020-11-23 15:55:46
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "邮箱服务器请求对象")
public class SaveMailServerRequestDTO {

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