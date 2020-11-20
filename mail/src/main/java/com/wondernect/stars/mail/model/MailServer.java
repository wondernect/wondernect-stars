package com.wondernect.stars.mail.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.mail.model.em.MailType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2017/10/26.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "mail_server")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮箱服务器")
public class MailServer extends BaseStringModel implements Serializable {

    @Enumerated(EnumType.STRING)
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
