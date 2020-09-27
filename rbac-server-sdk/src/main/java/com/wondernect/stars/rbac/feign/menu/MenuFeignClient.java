package com.wondernect.stars.rbac.feign.menu;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.feign.config.WondernectRbacFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@FeignClient(name = "${wondernect.stars.rbac.feign.name}", url = "${wondernect.stars.rbac.feign.url}", configuration = WondernectRbacFeignConfiguration.class)
public interface MenuFeignClient {

    @ApiOperation(value = "创建菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/menu/create")
    public BusinessData<MenuResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    );

    @ApiOperation(value = "更新菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/menu/{id}/update")
    public BusinessData<MenuResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveMenuRequestDTO saveMenuRequestDTO
    );

    @ApiOperation(value = "删除菜单", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/menu/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取菜单详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/menu/{id}/detail")
    public BusinessData<MenuResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "菜单列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/menu/list")
    public BusinessData<List<MenuResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListMenuRequestDTO listMenuRequestDTO
    );

    @ApiOperation(value = "菜单分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/menu/page")
    public BusinessData<PageResponseData<MenuResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageMenuRequestDTO pageMenuRequestDTO
    );

    @ApiOperation(value = "菜单树形结构", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/menu/{root_menu_id}/tree")
    public BusinessData<MenuTreeResponseDTO> tree(
            @ApiParam(required = true) @NotBlank(message = "根节点菜单不能为空") @PathVariable(value = "root_menu_id", required = false) String rootMenuId
    );
}
