package com.wondernect.stars.rbac.dto.rolemenuoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.PageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: PageRolePrivilegeRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色菜单分页请求对象")
public class PageRoleMenuOperationRequestDTO extends PageRequestDTO {

    @NotBlank(message = "角色id不能为空")
    @JsonProperty("role_code")
    @ApiModelProperty(notes = "角色id")
    private String roleId;

    @NotBlank(message = "菜单id不能为空")
    @JsonProperty("menu_code")
    @ApiModelProperty(notes = "菜单id")
    private String menuId;
}
