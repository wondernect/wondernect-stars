package com.wondernect.stars.database.server.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.*;
import com.wondernect.stars.database.service.DatabaseUserManageService;
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
 * 数据库用户接口
 *
 * @author liyafei 2020-11-09 15:58:16
 **/
@RequestMapping(value = "/v1/wondernect/database_user_manage")
@RestController
@Validated
@Api(tags = "数据库用户接口")
public class DatabaseUserManageController {

    @Autowired
    private DatabaseUserManageService databaseUserManageService;

    @AuthorizeServer
    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<DatabaseUserManageResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.create(saveDatabaseUserManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<DatabaseUserManageResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.update(id, saveDatabaseUserManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        databaseUserManageService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<DatabaseUserManageResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(databaseUserManageService.findById(id));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<DatabaseUserManageResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.list(listDatabaseUserManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<DatabaseUserManageResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.page(pageDatabaseUserManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "赋权限", httpMethod = "POST")
    @PostMapping(value = "/{type}/give_rights")
    public BusinessData<DatabaseUserManageResponseDTO> giveRights(
            @ApiParam(required = true) @NotNull(message = "1-只读权限，2-所有权限") @PathVariable(value = "type", required = false) Integer type,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseUserRequestDTO databaseUserRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.giveRights(type, databaseUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "收回权限", httpMethod = "POST")
    @PostMapping(value = "/revoke_rights")
    public BusinessData<DatabaseUserManageResponseDTO> revokeRights(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseUserRequestDTO databaseUserRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.revokeRights(databaseUserRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "测试连接", httpMethod = "POST")
    @PostMapping(value = "/test_connect")
    public BusinessData<TestConnectResponseDTO> testConnect(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseConnectRequestDTO databaseConnectRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.testConnect(databaseConnectRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @PostMapping(value = "/modify_password")
    public BusinessData<DatabaseUserManageResponseDTO> modifyPassword(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO
    ) {
        return new BusinessData<>(databaseUserManageService.modifyPassword(databaseModifyPasswordRequestDTO));
    }

}