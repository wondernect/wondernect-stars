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
import javax.persistence.Table;

/**
 * Created on 2017/10/6.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "应用")
public class App extends BaseStringModel {

    @JsonProperty("name")
    @ApiModelProperty(notes = "名称")
    private String name;

    @JsonProperty("secret")
    @ApiModelProperty(notes = "访问秘钥")
    private String secret;

    @JsonProperty("logo")
    @ApiModelProperty(notes = "logo")
    private String logo;

    @JsonProperty("brief")
    @ApiModelProperty(notes = "简介")
    private String brief;

    @JsonProperty("website")
    @ApiModelProperty(notes = "官网链接")
    private String website;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "绑定用户id")
    private String userId;
}
