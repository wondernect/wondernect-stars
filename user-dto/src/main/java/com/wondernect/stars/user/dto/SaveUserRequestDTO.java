package com.wondernect.stars.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.stars.user.em.Gender;
import com.wondernect.stars.user.em.UserType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SaveRoleRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户创建/更新请求对象")
public class SaveUserRequestDTO {

    @NotNull(message = "用户类型不能为空")
    @JsonProperty("user_type")
    @ApiModelProperty(notes = "用户类型", allowableValues = "LOCAL, THIRD")
    private UserType userType;

    @NotBlank(message = "用户登录名不能为空")
    @JsonProperty("username")
    @ApiModelProperty(notes = "用户登录名")
    private String username;

    @JsonProperty("name")
    @ApiModelProperty(notes = "姓名")
    private String name;

    @JsonProperty("gender")
    @ApiModelProperty(notes = "性别", allowableValues = "MALE, FEMALE, UNKNOWN")
    private Gender gender = Gender.UNKNOWN;

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

    @JsonProperty("remark")
    @ApiModelProperty(notes = "个性签名")
    private String remark;

    @JsonProperty("role_type")
    @ApiModelProperty(notes = "角色类型")
    private String roleType;

    @JsonProperty("role")
    @ApiModelProperty(notes = "角色")
    private String role;

    @JsonProperty("enable")
    @ApiModelProperty(notes = "是否可用")
    private Boolean enable = true;

    @JsonProperty("editable")
    @ApiModelProperty(notes = "是否可编辑")
    private Boolean editable = true;

    @JsonProperty("deletable")
    @ApiModelProperty(notes = "是否可删除")
    private Boolean deletable = true;
}
