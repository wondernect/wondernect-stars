package com.wondernect.stars.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.user.model.em.PasswordType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "user_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户认证")
public class UserAuth extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 1910890917070971121L;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @JsonProperty("password_type")
    @ApiModelProperty(notes = "密码类型")
    private PasswordType passwordType;

    @JsonProperty("password")
    @ApiModelProperty(notes = "密码")
    private String password;
}
