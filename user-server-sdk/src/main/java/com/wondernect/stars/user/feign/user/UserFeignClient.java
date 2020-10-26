package com.wondernect.stars.user.feign.user;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.feign.config.WondernectUserFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author:王威
 * @Date: 2020/8/12 11:21
 * @Version 1.0
 */
@FeignClient(name = "${wondernect.stars.user.feign.name}", url = "${wondernect.stars.user.feign.url}", configuration = WondernectUserFeignConfiguration.class)
public interface UserFeignClient {

    @ApiOperation(value = "创建local user", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/create")
    public BusinessData<UserResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    );

    @ApiOperation(value = "创建third user", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/create_third_user")
    public BusinessData<UserResponseDTO> createThirdUser(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveThirdUserRequestDTO saveThirdUserRequestDTO
    );

    @ApiOperation(value = "激活", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/{id}/enable")
    public BusinessData enable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "禁用", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/{id}/disable")
    public BusinessData disable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/{id}/update")
    public BusinessData<UserResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/user/{id}/detail")
    public BusinessData<UserResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/user/detail")
    public BusinessData<UserResponseDTO> detailByUsername(
            @ApiParam(required = true) @NotBlank(message = "用户username不能为空") @RequestParam(value = "username", required = false) String username
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/list")
    public BusinessData<List<UserResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListUserRequestDTO listUserRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/page")
    public BusinessData<PageResponseData<UserResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageUserRequestDTO pageUserRequestDTO
    );
}
