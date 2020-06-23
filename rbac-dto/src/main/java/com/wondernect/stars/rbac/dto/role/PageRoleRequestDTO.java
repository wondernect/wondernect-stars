package com.wondernect.stars.rbac.dto.role;

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
@ApiModel(value = "角色分页请求对象")
public class PageRoleRequestDTO {

    @JsonProperty("value")
    @ApiModelProperty(notes = "输入(代码或名称)")
    private String value;

    @JsonProperty("role_type")
    @ApiModelProperty(notes = "角色类型代码")
    private String roleType;

    @NotNull(message = "分页请求参数不能为空")
    @JsonProperty("page_request_data")
    @ApiModelProperty(notes = "分页参数")
    private PageRequestData pageRequestData;
}
