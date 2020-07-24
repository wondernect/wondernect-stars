package com.wondernect.stars.rbac.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.*;
import com.wondernect.stars.rbac.service.rolemenu.RoleMenuService;
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
 * FileName: RoleMenuController
 * Author: chenxun
 * Date: 2020-02-21 14:08
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/{application}/rbac/role_menu")
@Validated
@Api(tags = "角色-菜单", description = "角色-菜单")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @ApiOperation(value = "勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/add")
    public BusinessData create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    ) {
        roleMenuService.add(roleMenuRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "编辑勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/edit")
    public BusinessData update(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    ) {
        roleMenuService.edit(roleMenuRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "取消勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    ) {
        roleMenuService.delete(roleMenuRequestDTO);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取角色菜单", httpMethod = "GET")
    @GetMapping(value = "/detail")
    public BusinessData<RoleMenuResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id", required = false) String roleId,
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "menu_id", required = false) String menuId
    ) {
        return new BusinessData<>(roleMenuService.findByRoleIdAndMenuId(roleId, menuId));
    }

    @ApiOperation(value = "角色对应菜单树形结构", httpMethod = "GET")
    @GetMapping(value = "/tree")
    public BusinessData<RoleMenuTreeResponseDTO> tree(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id", required = false) String roleId,
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "menu_id", required = false) String menuId
    ) {
        return new BusinessData<>(roleMenuService.tree(roleId, menuId));
    }

    @ApiOperation(value = "角色对应权限", httpMethod = "POST")
    @GetMapping(value = "/authority")
    public BusinessData<List<MenuAuthorityResponseDTO>> roleAuthority(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id_list", required = false) List<String> roleIdList
    ) {
        return new BusinessData<>(roleMenuService.roleAuthority(roleIdList));
    }
}
