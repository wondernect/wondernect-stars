package com.wondernect.stars.session.feign.captchaSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.captcha.*;
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
@FeignClient(name = "${wondernect.stars.session.feign.name}", url = "${wondernect.stars.session.feign.url}", path = "/v1/wondernect/session/captcha", configuration = WondernectFeignConfiguration.class)
public interface CaptchaSessionFeignClient {

    @ApiOperation(value = "请求(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/request")
    public BusinessData<CaptchaResponseDTO> request(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody CaptchaRequestDTO captchaRequestDTO
    );

    @ApiOperation(value = "删除(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "验证码id不能为空") @PathVariable(value = "id", required = false) String captchaSessionId
    );

    @ApiOperation(value = "获取详情(缓存&数据库)", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<CaptchaResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "验证码id不能为空") @PathVariable(value = "id", required = false) String captchaSessionId
    );

    @ApiOperation(value = "删除(缓存)", httpMethod = "POST")
    @PostMapping(value = "/{id}/cache_delete")
    public BusinessData deleteCache(
            @ApiParam(required = true) @NotBlank(message = "验证码id不能为空") @PathVariable(value = "id", required = false) String captchaSessionId
    );

    @ApiOperation(value = "获取详情(缓存)", httpMethod = "GET")
    @GetMapping(value = "/{id}/cache_detail")
    public BusinessData<CaptchaResponseDTO> detailCache(
            @ApiParam(required = true) @NotBlank(message = "验证码id不能为空") @PathVariable(value = "id", required = false) String captchaSessionId
    );

    @ApiOperation(value = "验证(缓存)", httpMethod = "POST")
    @PostMapping(value = "/cache_auth")
    public BusinessData<CaptchaResponseDTO> authCache(
            @ApiParam(required = true) @NotNull(message = "验证请求参数不能为空") @Validated @RequestBody CaptchaAuthRequestDTO captchaAuthRequestDTO
    );

    @ApiOperation(value = "列表(数据库)", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<CaptchaResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListCaptchaRequestDTO listCaptchaRequestDTO
    );

    @ApiOperation(value = "分页(数据库)", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<CaptchaResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageCaptchaRequestDTO pageCaptchaRequestDTO
    );

}
