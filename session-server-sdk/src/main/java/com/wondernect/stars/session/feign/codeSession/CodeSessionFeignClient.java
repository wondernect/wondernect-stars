package com.wondernect.stars.session.feign.codeSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.code.*;
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
 * @Author:王威
 * @Date: 2020/8/12 11:21
 * @Version 1.0
 */
@FeignClient(name = "${wondernect.stars.session.feign.name}", url = "${wondernect.stars.session.feign.url}", path = "/v1/wondernect/session/code", configuration = WondernectFeignConfiguration.class)
public interface CodeSessionFeignClient {

    @ApiOperation(value = "请求(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/request")
    public BusinessData<CodeResponseDTO> request(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody CodeRequestDTO codeRequestDTO
    );

    @ApiOperation(value = "删除(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/{code}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    );

    @ApiOperation(value = "获取(缓存&数据库)", httpMethod = "GET")
    @GetMapping(value = "/{code}/detail")
    public BusinessData<CodeResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    );

    @ApiOperation(value = "删除(缓存)", httpMethod = "POST")
    @PostMapping(value = "/{code}/cache_delete")
    public BusinessData deleteCache(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    );

    @ApiOperation(value = "获取(缓存)", httpMethod = "GET")
    @GetMapping(value = "/{code}/cache_detail")
    public BusinessData<CodeResponseDTO> detailCache(
            @ApiParam(required = true) @NotBlank(message = "令牌code不能为空") @PathVariable(value = "code", required = false) String code
    );

    @ApiOperation(value = "续约/刷新(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/refresh")
    public BusinessData<CodeResponseDTO> refresh(
            @ApiParam(required = true) @NotNull(message = "刷新请求参数不能为空") @Validated @RequestBody CodeRefreshRequestDTO codeRefreshRequestDTO
    );

    @ApiOperation(value = "验证(缓存)", httpMethod = "POST")
    @PostMapping(value = "/cache_auth")
    public BusinessData<CodeResponseDTO> authCache(
            @ApiParam(required = true) @NotNull(message = "验证请求参数不能为空") @Validated @RequestBody CodeAuthRequestDTO codeAuthRequestDTO
    );

    @ApiOperation(value = "列表(数据库)", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<CodeResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListCodeRequestDTO listCodeRequestDTO
    );

    @ApiOperation(value = "分页(数据库)", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<CodeResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageCodeRequestDTO pageCodeRequestDTO
    );
}
