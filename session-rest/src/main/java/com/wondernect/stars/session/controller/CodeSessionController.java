package com.wondernect.stars.session.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.code.*;
import com.wondernect.stars.session.service.code.CodeSessionService;
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
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CodeSessionController
 * Author: chenxun
 * Date: 2019/6/5 14:44
 * Description: 令牌会话
 */
@Api(tags = "临时会话", description = "临时会话")
@Validated
@RestController
@RequestMapping(value = "/v1/wondernect/session/code")
public class CodeSessionController {

    @Autowired
    private CodeSessionService codeSessionService;

    @AuthorizeServer
    @ApiOperation(value = "请求(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/request")
    public BusinessData<CodeResponseDTO> request(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody CodeRequestDTO codeRequestDTO
    ) {
        return new BusinessData<>(codeSessionService.requestCodeSession(codeRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/{code}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    ) {
        codeSessionService.deleteByCode(code);
        return new BusinessData<>(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取(缓存&数据库)", httpMethod = "GET")
    @GetMapping(value = "/{code}/detail")
    public BusinessData<CodeResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    ) {
        return new BusinessData<>(codeSessionService.findByCode(code));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除(缓存)", httpMethod = "POST")
    @PostMapping(value = "/{code}/cache_delete")
    public BusinessData deleteCache(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    ) {
        codeSessionService.deleteCacheByCode(code);
        return new BusinessData<>(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取(缓存)", httpMethod = "GET")
    @GetMapping(value = "/{code}/cache_detail")
    public BusinessData<CodeResponseDTO> detailCache(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    ) {
        return new BusinessData<>(codeSessionService.findCacheByCode(code));
    }

    @AuthorizeServer
    @ApiOperation(value = "续约/刷新(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/refresh")
    public BusinessData<CodeResponseDTO> refresh(
            @ApiParam(required = true) @NotNull(message = "刷新请求参数不能为空") @Validated @RequestBody CodeRefreshRequestDTO codeRefreshRequestDTO
    ) {
        return new BusinessData<>(codeSessionService.refreshCodeSession(codeRefreshRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "验证(缓存)", httpMethod = "POST")
    @PostMapping(value = "/cache_auth")
    public BusinessData<CodeResponseDTO> authCache(
            @ApiParam(required = true) @NotNull(message = "验证请求参数不能为空") @Validated @RequestBody CodeAuthRequestDTO codeAuthRequestDTO
    ) {
        return new BusinessData<>(codeSessionService.authCodeSession(codeAuthRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表(数据库)", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<CodeResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListCodeRequestDTO listCodeRequestDTO
    ) {
        return new BusinessData<>(codeSessionService.list(listCodeRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页(数据库)", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<CodeResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageCodeRequestDTO pageCodeRequestDTO
    ) {
        return new BusinessData<>(codeSessionService.page(pageCodeRequestDTO));
    }
}
