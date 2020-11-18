package com.wondernect.stars.database.feign.databaseUserManage;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.*;
import com.wondernect.stars.database.feign.config.WondernectDatabaseFeignConfiguration;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "${wondernect.stars.database.feign.name}", url = "${wondernect.stars.database.feign.url}", configuration = WondernectDatabaseFeignConfiguration.class)
public interface DatabaseUserManageFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/create")
    BusinessData<DatabaseUserManageResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/{id}/update")
    BusinessData<DatabaseUserManageResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserManageRequestDTO saveDatabaseUserManageRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/{id}/delete")
    BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/database/database_user_manage/{id}/detail")
    BusinessData<DatabaseUserManageResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/list")
    BusinessData<List<DatabaseUserManageResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseUserManageRequestDTO listDatabaseUserManageRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/page")
    BusinessData<PageResponseData<DatabaseUserManageResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageDatabaseUserManageRequestDTO pageDatabaseUserManageRequestDTO
    );

    @ApiOperation(value = "赋权限", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/{type}/give_rights")
    BusinessData<DatabaseUserManageResponseDTO> giveRights(
            @ApiParam(required = true) @NotBlank(message = "1-只读权限，2-所有权限") @PathVariable(value = "type", required = false) int type,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseUserRequestDTO databaseUserRequestDTO
    );

    @ApiOperation(value = "收回权限", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/revoke_rights")
    BusinessData<DatabaseUserManageResponseDTO> revokeRights(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseUserRequestDTO databaseUserRequestDTO
    );

    @ApiOperation(value = "测试连接", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/test_connect")
    BusinessData<TestConnectResponseDTO> testConnect(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseConnectRequestDTO databaseConnectRequestDTO
    );

    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database/database_user_manage/modify_password")
    BusinessData<DatabaseUserManageResponseDTO> modifyPassword(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseModifyPasswordRequestDTO databaseModifyPasswordRequestDTO
    );

}
