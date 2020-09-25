package com.wondernect.stars.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.ListRequestDTO;
import com.wondernect.elements.rdb.request.SortData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ListPrivilegeRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户列表请求对象")
public class ListUserRequestDTO extends ListRequestDTO {

    @JsonProperty("username")
    @ApiModelProperty(notes = "用户登录名")
    private String username;

    @JsonProperty("role_type_id")
    @ApiModelProperty(notes = "角色类型")
    private String roleTypeId;

    @JsonProperty("role_id")
    @ApiModelProperty(notes = "角色")
    private String roleId;

    @JsonProperty("enable")
    @ApiModelProperty(notes = "是否可用")
    private Boolean enable;
}

