package com.wondernect.stars.user.feign.user;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.em.AppType;
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
@FeignClient(name = "${wondernect.stars.user.feign.name}", url = "${wondernect.stars.user.feign.url}", path = "/v1/wondernect/user", configuration = WondernectFeignConfiguration.class)
public interface UserFeignClient {

    @ApiOperation(value = "创建local user", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<UserResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    );

    @ApiOperation(value = "创建third user", httpMethod = "POST")
    @PostMapping(value = "/create_third_user")
    public BusinessData<UserResponseDTO> createThirdUser(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveThirdUserRequestDTO saveThirdUserRequestDTO
    );

    @ApiOperation(value = "激活", httpMethod = "POST")
    @PostMapping(value = "/{id}/enable")
    public BusinessData enable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "禁用", httpMethod = "POST")
    @PostMapping(value = "/{id}/disable")
    public BusinessData disable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<UserResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<UserResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/detail")
    public BusinessData<UserResponseDTO> detailByUsername(
            @ApiParam(required = true) @NotBlank(message = "用户username不能为空") @RequestParam(value = "username", required = false) String username
    );

    @ApiOperation(value = "获取第三方用户详情", httpMethod = "GET")
    @GetMapping(value = "/detail_third_user")
    public BusinessData<UserResponseDTO> detailByAppTypeAndAppUserId(
            @ApiParam(required = true) @NotNull(message = "第三方应用类型不能为空") @RequestParam(value = "app_type", required = false) AppType appType,
            @ApiParam(required = true) @NotBlank(message = "第三方应用用户id不能为空") @RequestParam(value = "app_user_id", required = false) String appUserId
    );

    @ApiOperation(value = "获取详情(只获取用户基础信息)", httpMethod = "GET")
    @GetMapping(value = "/{id}/exist")
    public BusinessData<UserResponseDTO> exist(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    );

    @ApiOperation(value = "验证本地用户", httpMethod = "POST")
    @PostMapping(value = "/auth")
    public BusinessData authLocalUser(
            @ApiParam(required = true) @NotNull(message = "认证请求参数不能为空") @Validated @RequestBody AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO
    );

    @ApiOperation(value = "验证第三方用户", httpMethod = "POST")
    @PostMapping(value = "/auth_third_user")
    public BusinessData authThirdUser(
            @ApiParam(required = true) @NotNull(message = "认证请求参数不能为空") @Validated @RequestBody AuthUserThirdAuthRequestDTO authUserThirdAuthRequestDTO
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<UserResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListUserRequestDTO listUserRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<UserResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageUserRequestDTO pageUserRequestDTO
    );
}
