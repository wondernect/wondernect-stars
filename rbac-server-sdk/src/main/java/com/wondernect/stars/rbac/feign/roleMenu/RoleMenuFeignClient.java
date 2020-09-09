package com.wondernect.stars.rbac.feign.roleMenu;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.MenuAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.RoleAuthorityResponseDTO;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuRequestDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuResponseDTO;
import com.wondernect.stars.rbac.dto.rolemenu.RoleMenuTreeResponseDTO;
import com.wondernect.stars.rbac.feign.config.WondernectRbacFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public interface RoleMenuFeignClient {

    @ApiOperation(value = "勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu/add")
    public BusinessData create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    );

    @ApiOperation(value = "编辑勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu/edit")
    public BusinessData update(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    );

    @ApiOperation(value = "取消勾选菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) RoleMenuRequestDTO roleMenuRequestDTO
    );

    @ApiOperation(value = "获取角色菜单", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/role_menu/detail")
    public BusinessData<RoleMenuResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id", required = false) String roleId,
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "menu_id", required = false) String menuId
    );

    @ApiOperation(value = "角色对应菜单树形结构", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/role_menu/tree")
    public BusinessData<RoleMenuTreeResponseDTO> tree(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id", required = false) String roleId,
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "menu_id", required = false) String menuId
    );

    @ApiOperation(value = "角色对应权限", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu/{role_id}/authority")
    public BusinessData<RoleAuthorityResponseDTO> roleAuthority(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @PathVariable(value = "role_id", required = false) String roleId
    );

    @ApiOperation(value = "角色对应权限", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role_menu/authority")
    public BusinessData<List<MenuAuthorityResponseDTO>> roleAuthority(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "role_id_list", required = false) List<String> roleIdList
    );

}
