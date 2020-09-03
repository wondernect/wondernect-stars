package com.wondernect.stars.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@ApiModel(value = "用户分页请求对象")
public class PageUserRequestDTO {

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

    @NotNull(message = "分页请求参数不能为空")
    @JsonProperty("page_request_data")
    @ApiModelProperty(notes = "分页参数")
    private PageRequestData pageRequestData;
}
