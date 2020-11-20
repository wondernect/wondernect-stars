package com.wondernect.stars.mail.model;

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
 * Created on 2017/10/26.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "mail_kind")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "邮箱类别")
public class MailKind extends BaseStringModel implements Serializable {

    @JsonProperty("username")
    @ApiModelProperty(notes = "邮箱地址")
    private String username;

    @JsonProperty("code")
    @ApiModelProperty(notes = "授权码")
    private String code;

    @JsonProperty("host")
    @ApiModelProperty(notes = "服务器地址")
    private String host;

    @JsonProperty("port")
    @ApiModelProperty(notes = "端口")
    private String port;
}
