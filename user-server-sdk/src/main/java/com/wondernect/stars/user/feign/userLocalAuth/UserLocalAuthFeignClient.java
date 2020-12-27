package com.wondernect.stars.user.feign.userLocalAuth;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author:王威
 * @Date: 2020/8/12 11:21
 * @Version 1.0
 */
@FeignClient(name = "${wondernect.stars.user.feign.name}", url = "${wondernect.stars.user.feign.url}", path = "/v1/wondernect/user/local_auth", configuration = WondernectFeignConfiguration.class)
public interface UserLocalAuthFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/{id}/create")
    public BusinessData<UserLocalAuthResponseDTO> create(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<UserLocalAuthResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<UserLocalAuthResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "认证", httpMethod = "POST")
    @PostMapping(value = "/{id}/auth")
    public BusinessData<UserLocalAuthResponseDTO> auth(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "认证请求参数不能为空") @Validated @RequestBody AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO
    );
}
