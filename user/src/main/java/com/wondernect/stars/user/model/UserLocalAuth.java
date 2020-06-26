package com.wondernect.stars.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: UserAuth
 * Author: chenxun
 * Date: 2018/10/24 15:34
 * Description:
 */
@Entity
@Table(name = "user_local_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "本地用户认证")
public class UserLocalAuth extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1910890917070971121L;

    @Id
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @JsonProperty("password")
    @ApiModelProperty(notes = "密码")
    private String password;
}
