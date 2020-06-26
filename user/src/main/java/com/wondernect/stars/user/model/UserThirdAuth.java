package com.wondernect.stars.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.user.em.AppType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2017/10/6.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "user_third_auth")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "第三方用户认证")
public class UserThirdAuth extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = 2656274719976675020L;

    @JsonProperty("user_id")
    @ApiModelProperty(notes = "本地用户id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @JsonProperty("app_type")
    @ApiModelProperty(notes = "第三方注册类型")
    private AppType appType;

    @JsonProperty("app_user_id")
    @ApiModelProperty(notes = "第三方注册用户id")
    private String appUserId;

    @JsonProperty("app_user_name")
    @ApiModelProperty(notes = "第三方注册用户name")
    private String appUserName;

    @JsonProperty("app_user_avatar")
    @ApiModelProperty(notes = "第三方注册用户头像")
    private String appUserAvatar;
}
