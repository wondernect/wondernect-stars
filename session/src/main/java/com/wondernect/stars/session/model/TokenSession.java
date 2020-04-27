package com.wondernect.stars.session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseTokenModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by cxhome on 2017/8/23.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "token_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "令牌会话(无过期时间)")
public class TokenSession extends BaseTokenModel implements Serializable {

    private static final long serialVersionUID = -6815564653309538511L;

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

    @JsonProperty("device_identifier")
    @ApiModelProperty(notes = "客户端唯一标识(移动端可使用该标识进行消息推送)")
    private String deviceIdentifier;

    @NotBlank(message = "会话使用描述不能为空")
    @JsonProperty("description")
    @ApiModelProperty(notes = "会话使用描述")
    private String description;
}
