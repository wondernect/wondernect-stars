package com.wondernect.stars.rbac.dto.userrole;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserRoleRequestDTO
 * Author: chenxun
 * Date: 2019/8/23 10:43
 * Description: 用户角色请求对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户角色请求对象")
public class UserRoleRequestDTO {

    @NotBlank(message = "用户id不能为空")
    @JsonProperty("user_id")
    @ApiModelProperty(notes = "用户id")
    private String userId;

    @NotBlank(message = "角色id不能为空")
    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色id")
    private String roleId;
}
