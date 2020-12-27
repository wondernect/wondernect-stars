package com.wondernect.stars.database.feign.databaseUserRightsShip;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.feign.config.WondernectFeignConfiguration;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.*;
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

@FeignClient(name = "${wondernect.stars.database.feign.name}", url = "${wondernect.stars.database.feign.url}", path = "/v1/wondernect/database/database_user_rights_ship", configuration = WondernectFeignConfiguration.class)
public interface DatabaseUserRightsShipFeignClient {

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    BusinessData<DatabaseUserRightsShipResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    BusinessData<List<DatabaseUserRightsShipResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseUserRightsShipRequestDTO listDatabaseUserRightsShipRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    BusinessData<PageResponseData<DatabaseUserRightsShipResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageDatabaseUserRightsShipRequestDTO pageDatabaseUserRightsShipRequestDTO
    );

    @ApiOperation(value = "赋权限", httpMethod = "POST")
    @PostMapping(value = "/{type}/give_rights")
    BusinessData<DatabaseUserRightsShipResponseDTO> giveRights(
            @ApiParam(required = true) @NotNull(message = "1-只读权限，2-所有权限") @PathVariable(value = "type", required = false) Integer type,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO
    );

    @ApiOperation(value = "收回权限", httpMethod = "POST")
    @PostMapping(value = "/revoke_rights")
    BusinessData revokeRights(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseUserRightsShipRequestDTO saveDatabaseUserRightsShipRequestDTO
    );

    @ApiOperation(value = "测试连接", httpMethod = "POST")
    @PostMapping(value = "/test_connect")
    BusinessData<TestConnectResponseDTO> testConnect(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) DatabaseUserRequestDTO databaseUserRequestDTO
    );
}
