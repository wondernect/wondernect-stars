package com.wondernect.stars.user.feign.userThirdAuth;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.dto.userrole.ListUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.PageUserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleRequestDTO;
import com.wondernect.stars.user.dto.userrole.UserRoleResponseDTO;
import com.wondernect.stars.user.em.AppType;
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
public interface UserThirdAuthFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/third_auth/{id}/create")
    public BusinessData<UserThirdAuthResponseDTO> create(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/third_auth/{id}/update")
    public BusinessData<UserThirdAuthResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/third_auth/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "app类型不能为空") @RequestParam(value = "app_type", required = false) AppType appType
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/user/third_auth/{id}/detail")
    public BusinessData<UserThirdAuthResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "app类型不能为空") @RequestParam(value = "app_type", required = false) AppType appType
    );

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/user/third_auth/detail")
    public BusinessData<UserThirdAuthResponseDTO> detailByAppTypeAndAppUserId(
            @ApiParam(required = true) @NotNull(message = "app类型不能为空") @RequestParam(value = "app_type", required = false) AppType appType,
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @RequestParam(value = "app_user_id", required = false) String appUserId
    );

    @ApiOperation(value = "认证", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/user/third_auth/auth")
    public BusinessData<UserThirdAuthResponseDTO> list(
            @ApiParam(required = true) @NotNull(message = "认证请求参数不能为空") @Validated @RequestBody AuthUserThirdAuthRequestDTO authUserThirdAuthRequestDTO
    );
}
