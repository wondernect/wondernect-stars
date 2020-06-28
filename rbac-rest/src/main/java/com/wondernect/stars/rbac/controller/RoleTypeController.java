package com.wondernect.stars.rbac.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.roletype.ListRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.PageRoleTypeRequestDTO;
import com.wondernect.stars.rbac.dto.roletype.RoleTypeResponseDTO;
import com.wondernect.stars.rbac.dto.roletype.SaveRoleTypeRequestDTO;
import com.wondernect.stars.rbac.service.roletype.RoleTypeService;
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
 * FileName: RoleTypeController
 * Author: chenxun
 * Date: 2020-02-21 14:07
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/{application}/rbac/role_type")
@Validated
@Api(tags = "角色类型", description = "角色类型")
public class RoleTypeController {

    @Autowired
    private RoleTypeService roleTypeService;

    @ApiOperation(value = "创建角色类型", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<RoleTypeResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleTypeRequestDTO saveRoleTypeRequestDTO
    ) {
        return new BusinessData<>(roleTypeService.create(saveRoleTypeRequestDTO));
    }

    @ApiOperation(value = "更新角色类型", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<RoleTypeResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveRoleTypeRequestDTO saveRoleTypeRequestDTO
    ) {
        return new BusinessData<>(roleTypeService.update(id, saveRoleTypeRequestDTO));
    }

    @ApiOperation(value = "删除角色类型", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        roleTypeService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取角色类型", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<RoleTypeResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(roleTypeService.findById(id));
    }

    @ApiOperation(value = "角色类型列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<RoleTypeResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListRoleTypeRequestDTO listRoleTypeRequestDTO
    ) {
        return new BusinessData<>(roleTypeService.list(listRoleTypeRequestDTO));
    }

    @ApiOperation(value = "角色类型分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<RoleTypeResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageRoleTypeRequestDTO pageRoleTypeRequestDTO
    ) {
        return new BusinessData<>(roleTypeService.page(pageRoleTypeRequestDTO));
    }
}
