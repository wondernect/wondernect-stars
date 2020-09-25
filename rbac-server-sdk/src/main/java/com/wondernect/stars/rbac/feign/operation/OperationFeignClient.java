package com.wondernect.stars.rbac.feign.operation;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.rbac.dto.menu.*;
import com.wondernect.stars.rbac.dto.operation.ListOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.OperationResponseDTO;
import com.wondernect.stars.rbac.dto.operation.PageOperationRequestDTO;
import com.wondernect.stars.rbac.dto.operation.SaveOperationRequestDTO;
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
public interface OperationFeignClient {

    @ApiOperation(value = "创建操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/operation/create")
    public BusinessData<OperationResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveOperationRequestDTO saveOperationRequestDTO
    );

    @ApiOperation(value = "更新操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/operation/{id}/update")
    public BusinessData<OperationResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveOperationRequestDTO saveOperationRequestDTO
    );

    @ApiOperation(value = "删除操作", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/operation/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取操作详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/rbac/operation/{id}/detail")
    public BusinessData<OperationResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "操作列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/operation/list")
    public BusinessData<List<OperationResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListOperationRequestDTO listOperationRequestDTO
    );

    @ApiOperation(value = "操作分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/rbac/operation/page")
    public BusinessData<PageResponseData<OperationResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageOperationRequestDTO pageOperationRequestDTO
    );
}
