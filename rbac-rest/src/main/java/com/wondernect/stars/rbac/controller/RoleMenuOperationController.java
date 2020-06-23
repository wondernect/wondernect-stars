package com.wondernect.stars.rbac.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeRoleType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeUserRole;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.MenuOperationResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.ListRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.PageRoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenuoperation.RoleMenuOperationResponseDTO;
import com.wondernect.stars.rbac.service.RoleMenuOperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleMenuOperationController
 * Author: chenxun
 * Date: 2020-02-21 14:08
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/wondernect/rbac/role_menu_operation")
@Validated
@Api(tags = "角色-菜单-操作管理", description = "角色-菜单-操作管理")
public class RoleMenuOperationController {

    @Autowired
    private RoleMenuOperationService roleMenuOperationService;

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "勾选操作", httpMethod = "POST")
    @PostMapping(value = "/add")
    public BusinessData add(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    ) {
        roleMenuOperationService.add(roleMenuOperationRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "编辑勾选操作", httpMethod = "POST")
    @PostMapping(value = "/edit")
    public BusinessData edit(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    ) {
        roleMenuOperationService.edit(roleMenuOperationRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "取消勾选操作", httpMethod = "POST")
    @PostMapping(value = "/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuOperationRequestDTO roleMenuOperationRequestDTO
    ) {
        roleMenuOperationService.delete(roleMenuOperationRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "获取角色菜单对应操作的相关信息", httpMethod = "GET", hidden = true)
    @GetMapping(value = "/detail")
    public BusinessData<MenuOperationResponseDTO> getRoleMenuOperation(
            @ApiParam(required = true) @NotBlank(message = "角色不能为空") @RequestParam(value = "role_code", required = false) String roleCode,
            @ApiParam(required = true) @NotBlank(message = "菜单不能为空") @RequestParam(value = "menu_code", required = false) String menuCode,
            @ApiParam(required = true) @NotBlank(message = "操作不能为空") @RequestParam(value = "operation_code", required = false) String operationCode
    ) {
        return new BusinessData<>(roleMenuOperationService.getRoleMenuOperation(roleCode, menuCode, operationCode));
    };

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "角色权限菜单操作列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<RoleMenuOperationResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListRoleMenuOperationRequestDTO listRoleMenuOperationRequestDTO
    ) {
        return new BusinessData<>(roleMenuOperationService.list(listRoleMenuOperationRequestDTO));
    }

    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "角色权限菜单操作分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<RoleMenuOperationResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageRoleMenuOperationRequestDTO pageRoleMenuOperationRequestDTO
    ) {
        return new BusinessData<>(roleMenuOperationService.page(pageRoleMenuOperationRequestDTO));
    }
}
