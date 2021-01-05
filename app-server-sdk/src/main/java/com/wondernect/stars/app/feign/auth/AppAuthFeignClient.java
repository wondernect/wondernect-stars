package com.wondernect.stars.app.feign.auth;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.auth.AppAuthResponseDTO;
import com.wondernect.stars.app.dto.auth.ListAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.PageAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.SaveAppAuthRequestDTO;
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
 * 当@FeignClient有name和url还有configuration时，取值为url的地址,name只是为一个名称而已(无意义)
 * 当@FeignClient只有name和configuration时，name的取值为注册中心项目的名称即虚拟地址
 */
@FeignClient(name = "${wondernect.stars.app.feign.name}", url = "${wondernect.stars.app.feign.url}", path = "/v1/wondernect/app/auth", configuration = WondernectFeignConfiguration.class)
public interface AppAuthFeignClient {

    @ApiOperation(value = "创建", notes = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<AppAuthResponseDTO> create(
            @ApiParam(value = "创建请求对象", required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveAppAuthRequestDTO saveAppAuthRequestDTO
    );

    @ApiOperation(value = "更新", notes = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<AppAuthResponseDTO> update(
            @ApiParam(value = "对象id", required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(value = "更新请求对象", required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveAppAuthRequestDTO saveAppAuthRequestDTO
    );

    @ApiOperation(value = "删除", notes = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(value = "对象id", required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", notes = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<AppAuthResponseDTO> detail(
            @ApiParam(value = "对象id", required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "检查是否存在(返回缓存基本信息)", notes = "检查是否存在(返回缓存基本信息)", httpMethod = "GET")
    @GetMapping(value = "/exist")
    public BusinessData<AppAuthResponseDTO> exist(
            @ApiParam(value = "应用id", required = true) @NotBlank(message = "应用id不能为空") @PathVariable(value = "app_id", required = false) String appId,
            @ApiParam(value = "用户id", required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "user_id", required = false) String userId
    );

    @ApiOperation(value = "列表", notes = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<AppAuthResponseDTO>> list(
            @ApiParam(value = "列表请求对象", required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListAppAuthRequestDTO listAppAuthRequestDTO
    );

    @ApiOperation(value = "分页", notes = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<AppAuthResponseDTO>> page(
            @ApiParam(value = "分页请求对象", required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageAppAuthRequestDTO pageAppAuthRequestDTO
    );
}
