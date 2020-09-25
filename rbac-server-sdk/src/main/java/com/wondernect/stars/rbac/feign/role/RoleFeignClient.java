package com.wondernect.stars.rbac.feign.role;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.dto.role.ListRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.PageRoleRequestDTO;
import com.wondernect.stars.rbac.dto.role.RoleResponseDTO;
import com.wondernect.stars.rbac.dto.role.SaveRoleRequestDTO;
import com.wondernect.stars.rbac.feign.config.WondernectRbacFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
public interface RoleFeignClient {

    @ApiOperation(value = "创建角色", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role/create")
    public BusinessData<RoleResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleRequestDTO saveRoleRequestDTO
    );

    @ApiOperation(value = "更新角色", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role/{id}/update")
    public BusinessData<RoleResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleRequestDTO saveRoleRequestDTO
    );

    @ApiOperation(value = "删除角色", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取角色详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/role/{id}/detail")
    public BusinessData<RoleResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "角色列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role/list")
    public BusinessData<List<RoleResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListRoleRequestDTO listRoleRequestDTO
    );

    @ApiOperation(value = "角色分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/role/page")
    public BusinessData<PageResponseData<RoleResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageRoleRequestDTO pageRoleRequestDTO
    );

}
