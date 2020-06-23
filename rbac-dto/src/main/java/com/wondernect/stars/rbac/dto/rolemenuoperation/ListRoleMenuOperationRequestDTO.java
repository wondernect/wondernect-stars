package com.wondernect.stars.rbac.dto.rolemenuoperation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wondernect.elements.rdb.request.SortData;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: ListOperationRequestDTO
 * Author: chenxun
 * Date: 2019/6/3 14:02
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色菜单操作列表请求对象")
public class ListRoleMenuOperationRequestDTO {

    @NotBlank(message = "角色代码不能为空")
    @JsonProperty("role_code")
    @ApiModelProperty(notes = "角色代码")
    private String roleCode;

    @NotBlank(message = "菜单代码不能为空")
    @JsonProperty("menu_code")
    @ApiModelProperty(notes = "菜单代码")
    private String menuCode;

    @JsonProperty("sort_data_list")
    @ApiModelProperty(notes = "列表排序参数")
    private List<SortData> sortDataList;
}
