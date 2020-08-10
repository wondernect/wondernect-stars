package com.wondernect.stars.rbac.feign.roleMenuOperation;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;
import com.wondernect.stars.rbac.feign.config.WondernectRbacFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DepartmentFeignService
 * Author: chenxun
 * Date: 2019/8/1 19:37
 * Description: 部门服务
 */
@FeignClient(value = "wondernect-stars-rbac", configuration = WondernectRbacFeignConfiguration.class)
public interface RoleMenuOperationFeignClient {

    @ApiOperation(value = "勾选操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu_operation/add")
    public BusinessData add(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    );

    @ApiOperation(value = "编辑勾选操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu_operation/edit")
    public BusinessData edit(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    );

    @ApiOperation(value = "取消勾选操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu_operation/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    );

    @ApiOperation(value = "获取角色菜单对应操作的相关信息", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/role_menu_operation/detail")
    public BusinessData<RoleMenuOperationResponseDTO> getRoleMenuOperation(
            @ApiParam(required = true) @NotBlank(message = "角色不能为空") @RequestParam(value = "role_id", required = false) String roleId,
            @ApiParam(required = true) @NotBlank(message = "菜单不能为空") @RequestParam(value = "menu_id", required = false) String menuId,
            @ApiParam(required = true) @NotBlank(message = "操作不能为空") @RequestParam(value = "operation_id", required = false) String operationId
    );

    @ApiOperation(value = "角色权限菜单操作列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu_operation/list")
    public BusinessData<List<RoleMenuOperationResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListRoleMenuOperationRequestDTO listRoleMenuOperationRequestDTO
    );

    @ApiOperation(value = "角色权限菜单操作分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu_operation/page")
    public BusinessData<PageResponseData<RoleMenuOperationResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageRoleMenuOperationRequestDTO pageRoleMenuOperationRequestDTO
    );

}
