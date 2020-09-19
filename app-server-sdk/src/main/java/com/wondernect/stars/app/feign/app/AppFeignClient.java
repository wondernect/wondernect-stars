package com.wondernect.stars.app.feign.app;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.AppResponseDTO;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.dto.ListAppRequestDTO;
import com.wondernect.stars.app.dto.PageAppRequestDTO;
import com.wondernect.stars.app.feign.config.WondernectAppFeignConfiguration;
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
 * Copyright (C), 2020, wondernect.com
 * FileName: AppFeignClient
 * Author: chenxun
 * Date: 2020-09-13 23:23
 * Description:
 */
@FeignClient(value = "wondernect-stars-app", configuration = WondernectAppFeignConfiguration.class)
public interface AppFeignClient {

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/app/{id}/detail")
    public BusinessData<AppResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "认证应用密钥", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/app/{id}/auth")
    public BusinessData auth(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "认证请求参数不能为空") @Validated @RequestBody(required = false) AuthAppRequestDTO authAppRequestDTO
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/app/list")
    public BusinessData<List<AppResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListAppRequestDTO listAppRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/app/page")
    public BusinessData<PageResponseData<AppResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageAppRequestDTO pageAppRequestDTO
    );
}
