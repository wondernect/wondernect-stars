package com.wondernect.stars.rbac.feign.roleType;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;
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
@FeignClient(name = "${wondernect.stars.rbac.feign.name}", url = "${wondernect.stars.rbac.feign.url}", path = "/v1/wondernect/rbac/role_type", configuration = WondernectFeignConfiguration.class)
public interface RoleTypeFeignClient {

    @ApiOperation(value = "创建角色类型", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<RoleTypeResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleTypeRequestDTO saveRoleTypeRequestDTO
    );

    @ApiOperation(value = "更新角色类型", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<RoleTypeResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleTypeRequestDTO saveRoleTypeRequestDTO
    );

    @ApiOperation(value = "删除角色类型", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取角色类型", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<RoleTypeResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "角色类型列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<RoleTypeResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListRoleTypeRequestDTO listRoleTypeRequestDTO
    );

    @ApiOperation(value = "角色类型分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<RoleTypeResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageRoleTypeRequestDTO pageRoleTypeRequestDTO
    );

}
