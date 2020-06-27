package com.wondernect.stars.user.controller;

import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.SaveUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.service.astract.dfault.UserService;
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
@RequestMapping(value = "/v1/{application}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<UserResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserRequestDTO saveUserRequestDTO
    ) {
        return new BusinessData<>(userService.create(saveUserRequestDTO));
    }

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<UserResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody SaveUserRequestDTO saveUserRequestDTO
    ) {
        return new BusinessData<>(userService.update(userId, saveUserRequestDTO));
    }

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    ) {
        userService.deleteById(userId);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<UserResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "用户id不能为空") @PathVariable(value = "id", required = false) String userId
    ) {
        return new BusinessData<>(userService.findById(userId));
    }

    @ApiOperation(value = "获取详情", httpMethod = "GET")
    @GetMapping(value = "/detail")
    public BusinessData<UserResponseDTO> detailByUsername(
            @ApiParam(required = true) @NotBlank(message = "用户username不能为空") @RequestParam(value = "username", required = false) String username
    ) {
        return new BusinessData<>(userService.findByUsername(username));
    }

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<UserResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody ListUserRequestDTO listUserRequestDTO
    ) {
        return new BusinessData<>(userService.list(listUserRequestDTO));
    }

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<UserResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody PageUserRequestDTO pageUserRequestDTO
    ) {
        return new BusinessData<>(userService.page(pageUserRequestDTO));
    }
}
