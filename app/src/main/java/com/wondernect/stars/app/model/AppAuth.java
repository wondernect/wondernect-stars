package com.wondernect.stars.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created on 2017/10/6.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "app_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "应用访问认证")
public class AppAuth extends BaseStringModel {

    @Column(columnDefinition = "not null")
    @JsonProperty("app_id")
    @ApiModelProperty(notes = "应用id")
    private String appId;

    @Column(columnDefinition = "not null")
    @JsonProperty("secret")
    @ApiModelProperty(notes = "应用访问密钥")
    private String secret;

    @Column(columnDefinition = "not null")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "应用访问密钥绑定用户id")
    private String userId;

    @JsonProperty("visible")
    @ApiModelProperty(notes = "是否可见")
    private Boolean visible = true;
}
