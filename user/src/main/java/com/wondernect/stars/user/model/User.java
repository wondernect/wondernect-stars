package com.wondernect.stars.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.base.model.BaseStringModel;
import com.wondernect.stars.user.model.em.Gender;
import com.wondernect.stars.user.model.em.UserRegistType;
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
 * Created on 2017/10/6.
 * wondernect.com
 * @author sunbeam
 */
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "用户")
public class User extends BaseStringModel implements Serializable {

    private static final long serialVersionUID = -3562442016599771125L;

    @Enumerated(EnumType.STRING)
    @JsonProperty("user_regist_type")
    @ApiModelProperty(notes = "用户注册类型")
    private UserRegistType userRegistType;

    @Enumerated(EnumType.STRING)
    @JsonProperty("gender")
    @ApiModelProperty(notes = "性别")
    private Gender gender;

    @JsonProperty("name")
    @ApiModelProperty(notes = "姓名")
    private String name;

    @JsonProperty("avatar")
    @ApiModelProperty(notes = "头像")
    private String avatar;

    @JsonProperty("mobile")
    @ApiModelProperty(notes = "手机")
    private String mobile;

    @JsonProperty("email")
    @ApiModelProperty(notes = "邮箱")
    private String email;

    @JsonProperty("location")
    @ApiModelProperty(notes = "坐标(地理位置)")
    private String location;
}
