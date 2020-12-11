package com.wondernect.stars.session.feign.tokenSession;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.session.dto.token.*;
import com.wondernect.stars.session.feign.config.WondernectSessionFeignConfiguration;
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
@FeignClient(name = "${wondernect.stars.session.feign.name}", url = "${wondernect.stars.session.feign.url}", path = "/v1/wondernect/session/token", configuration = WondernectSessionFeignConfiguration.class)
public interface TokenSessionFeignClient {

    @ApiOperation(value = "请求令牌(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/request")
    public BusinessData<TokenResponseDTO> request(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody TokenRequestDTO tokenRequestDTO
    );

    @ApiOperation(value = "删除令牌(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/{token}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "令牌不能为空") @PathVariable(value = "token", required = false) String token
    );

    @ApiOperation(value = "获取令牌(缓存&数据库)", httpMethod = "GET")
    @GetMapping(value = "/{token}/detail")
    public BusinessData<TokenResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "令牌不能为空") @PathVariable(value = "token", required = false) String token
    );

    @ApiOperation(value = "刷新令牌(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/refresh")
    public BusinessData<TokenResponseDTO> refresh(
            @ApiParam(required = true) @NotNull(message = "刷新请求参数不能为空") @Validated @RequestBody TokenRefreshRequestDTO tokenRefreshRequestDTO
    );

    @ApiOperation(value = "验证令牌(缓存&数据库)", httpMethod = "POST")
    @PostMapping(value = "/auth")
    public BusinessData<TokenResponseDTO> auth(
            @ApiParam(required = true) @NotNull(message = "验证请求参数不能为空") @Validated @RequestBody TokenAuthRequestDTO tokenAuthRequestDTO
    );

    @ApiOperation(value = "临时会话列表(数据库)", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<TokenResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListTokenRequestDTO listTokenRequestDTO
    );

    @ApiOperation(value = "临时会话分页(数据库)", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<TokenResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageTokenRequestDTO pageTokenRequestDTO
    );
}
