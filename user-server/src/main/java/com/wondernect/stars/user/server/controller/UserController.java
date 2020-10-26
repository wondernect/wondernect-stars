package com.wondernect.stars.user.server.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.service.user.UserService;
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
 * FileName: UserController
 * Author: chenxun
 * Date: 2019/6/5 14:43
 * Description: 用户
 */
@Api(tags = "用户", description = "用户")
@Validated
@RestController
@RequestMapping(value = "/v1/wondernect/user")
public class UserController {

    @Autowired
    private UserService userService;

    @AuthorizeServer
    @ApiOperation(value = "激活", httpMethod = "POST")
    @PostMapping(value = "/{id}/enable")
    public BusinessData enable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        userService.enable(id, true);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "禁用", httpMethod = "POST")
    @PostMapping(value = "/{id}/disable")
    public BusinessData disable(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        userService.enable(id, false);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "创建local user", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<UserResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    ) {
        return new BusinessData<>(userService.createLocalUser(saveLocalUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "创建third user", httpMethod = "POST")
    @PostMapping(value = "/create_third_user")
    public BusinessData<UserResponseDTO> createThirdUser(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveThirdUserRequestDTO saveThirdUserRequestDTO
    ) {
        return new BusinessData<>(userService.createThirdUser(saveThirdUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<UserResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveLocalUserRequestDTO saveLocalUserRequestDTO
    ) {
        return new BusinessData<>(userService.update(userId, saveLocalUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    ) {
        userService.deleteById(userId);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<UserResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    ) {
        return new BusinessData<>(userService.findById(userId));
    }

    @AuthorizeServer
    @ApiOperation(value = "获取本地用户详情", httpMethod = "GET")
    @GetMapping(value = "/detail")
    public BusinessData<UserResponseDTO> detailByUsername(
            @ApiParam(required = true) @NotBlank(message = "用户username不能为空") @RequestParam(value = "username", required = false) String username
    ) {
        return new BusinessData<>(userService.findByUsername(username));
    }

    @AuthorizeServer
    @ApiOperation(value = "获取第三方用户详情", httpMethod = "GET")
    @GetMapping(value = "/detail_third_user")
    public BusinessData<UserResponseDTO> detailByAppTypeAndAppUserId(
            @ApiParam(required = true) @NotNull(message = "第三方应用类型不能为空") @RequestParam(value = "app_type", required = false) AppType appType,
            @ApiParam(required = true) @NotBlank(message = "第三方应用用户id不能为空") @RequestParam(value = "app_user_id", required = false) String appUserId
    ) {
        return new BusinessData<>(userService.findByAppTypeAndAppUserId(appType, appUserId));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<UserResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListUserRequestDTO listUserRequestDTO
    ) {
        return new BusinessData<>(userService.list(listUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<UserResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageUserRequestDTO pageUserRequestDTO
    ) {
        return new BusinessData<>(userService.page(pageUserRequestDTO));
    }
}
