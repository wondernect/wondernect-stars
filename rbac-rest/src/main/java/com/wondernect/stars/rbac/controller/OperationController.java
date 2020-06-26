package com.wondernect.stars.rbac.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.SaveOperationRequestDTO;
import com.wondernect.stars.rbac.service.astract.dfault.OperationService;
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
 * FileName: OperationController
 * Author: chenxun
 * Date: 2020-02-21 14:08
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/{application}/rbac/operation")
@Validated
@Api(tags = "操作", description = "操作")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @ApiOperation(value = "创建操作", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<OperationResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveOperationRequestDTO saveOperationRequestDTO
    ) {
        return new BusinessData<>(operationService.create(saveOperationRequestDTO));
    }

    @ApiOperation(value = "更新操作", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<OperationResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveOperationRequestDTO saveOperationRequestDTO
    ) {
        return new BusinessData<>(operationService.update(id, saveOperationRequestDTO));
    }

    @ApiOperation(value = "删除操作", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        operationService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取操作详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<OperationResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(operationService.findById(id));
    }

    @ApiOperation(value = "操作列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<OperationResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListOperationRequestDTO listOperationRequestDTO
    ) {
        return new BusinessData<>(operationService.list(listOperationRequestDTO));
    }

    @ApiOperation(value = "操作分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<OperationResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageOperationRequestDTO pageOperationRequestDTO
    ) {
        return new BusinessData<>(operationService.page(pageOperationRequestDTO));
    }
}
