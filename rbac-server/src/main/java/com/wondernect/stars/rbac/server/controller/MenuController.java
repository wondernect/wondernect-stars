package com.wondernect.stars.rbac.server.controller;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.model.Menu;
import com.wondernect.stars.rbac.server.config.RBACConfigProperties;
import com.wondernect.stars.rbac.service.menu.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: MenuController
 * Author: chenxun
 * Date: 2020-02-21 14:08
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/wondernect/rbac/menu")
@Validated
@Api(tags = "菜单", description = "菜单")
public class MenuController {

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private RBACConfigProperties rbacConfigProperties;

    @Autowired
    private MenuService menuService;

    @AuthorizeServer
    @ApiOperation(value = "创建菜单", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<MenuResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    ) {
        if (ESStringUtils.isBlank(saveMenuRequestDTO.getParentMenuId())) {
            saveMenuRequestDTO.setParentMenuId(rbacConfigProperties.getRootMenuId());
        }
        return new BusinessData<>(menuService.create(saveMenuRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "更新菜单", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<MenuResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    ) {
        return new BusinessData<>(menuService.update(id, saveMenuRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除菜单", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        menuService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取菜单详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<MenuResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(menuService.findById(id));
    }

    @AuthorizeServer
    @ApiOperation(value = "菜单列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<MenuResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListMenuRequestDTO listMenuRequestDTO
    ) {
        if (ESStringUtils.isBlank(listMenuRequestDTO.getParentMenuId())) {
            listMenuRequestDTO.setParentMenuId(rbacConfigProperties.getRootMenuId());
        }
        return new BusinessData<>(menuService.list(listMenuRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "菜单分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<MenuResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageMenuRequestDTO pageMenuRequestDTO
    ) {
        if (ESStringUtils.isBlank(pageMenuRequestDTO.getParentMenuId())) {
            pageMenuRequestDTO.setParentMenuId(rbacConfigProperties.getRootMenuId());
        }
        return new BusinessData<>(menuService.page(pageMenuRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "菜单树形结构", httpMethod = "GET")
    @GetMapping(value = "/tree")
    public BusinessData<MenuTreeResponseDTO> tree(
            @ApiParam(required = false) @RequestParam(value = "root_menu_id", required = false) String rootMenuId
    ) {
        if (ESStringUtils.isBlank(rootMenuId)) {
            rootMenuId = rbacConfigProperties.getRootMenuId();
        } else {
            Menu menu = menuService.findEntityById(rootMenuId);
            if (ESObjectUtils.isNull(menu)) {
                throw new BusinessException("菜单信息不存在");
            }
            if (!ESStringUtils.equals(menu.getCreateApp(), wondernectCommonContext.getAuthorizeData().getAppId())) {
                rootMenuId = rbacConfigProperties.getRootMenuId();
            }
        }
        return new BusinessData<>(menuService.tree(rootMenuId));
    }
}
