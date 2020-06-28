package com.wondernect.stars.rbac.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.service.menu.MenuService;
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
 * FileName: MenuController
 * Author: chenxun
 * Date: 2020-02-21 14:08
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/{application}/rbac/menu")
@Validated
@Api(tags = "菜单", description = "菜单")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "创建菜单", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<MenuResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    ) {
        return new BusinessData<>(menuService.create(saveMenuRequestDTO));
    }

    @ApiOperation(value = "更新菜单", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<MenuResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    ) {
        return new BusinessData<>(menuService.update(id, saveMenuRequestDTO));
    }

    @ApiOperation(value = "删除菜单", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        menuService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取菜单详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<MenuResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(menuService.findById(id));
    }

    @ApiOperation(value = "菜单列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<MenuResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListMenuRequestDTO listMenuRequestDTO
    ) {
        return new BusinessData<>(menuService.list(listMenuRequestDTO));
    }

    @ApiOperation(value = "菜单分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<MenuResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageMenuRequestDTO pageMenuRequestDTO
    ) {
        return new BusinessData<>(menuService.page(pageMenuRequestDTO));
    }

    @ApiOperation(value = "菜单树形结构", httpMethod = "GET")
    @GetMapping(value = "/{root_menu_code}/tree")
    public BusinessData<MenuTreeResponseDTO> tree(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "root_menu_code", required = false) String rootMenuCode
    ) {
        return new BusinessData<>(menuService.tree(rootMenuCode));
    }
}
