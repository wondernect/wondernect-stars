package com.wondernect.stars.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created on 2017/10/6.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(
        name = "app_auth",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})},
        indexes = {
                @Index(columnList = "appId"),
                @Index(columnList = "userId")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "应用访问认证")
public class AppAuth extends BaseStringModel {

    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用id")
    private String appId;

    @JsonProperty("secret")
    @ApiModelProperty(notes = "应用访问密钥")
    private String secret;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "应用访问密钥绑定用户id")
    private String userId;

    @JsonProperty("access_type")
    @ApiModelProperty(notes = "应用访问类型(1-只读；2-读写；)", allowableValues = "1, 2")
    private int accessType = 1;

    @JsonProperty("visible")
    @ApiModelProperty(notes = "是否可见")
    private Boolean visible = true;
}
