package com.wondernect.stars.session.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseCodeModel;
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
 * Created on 2017/9/17.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "code_session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "临时会话(有过期时间)")
public class CodeSession extends BaseCodeModel implements Serializable {

    private static final long serialVersionUID = -1056539037703520228L;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @JsonProperty("description")
    @ApiModelProperty(notes = "临时会话使用描述")
    private String description;

    @JsonProperty("expires")
    @ApiModelProperty(notes = "临时会话过期时间(默认7200s)")
    private Long expires;

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
}
