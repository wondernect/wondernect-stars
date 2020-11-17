package com.wondernect.stars.database.feign.databaseManage;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
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
public interface DatabaseManageFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/create")
    BusinessData<DatabaseManageResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/{id}/update")
    BusinessData<DatabaseManageResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/{id}/delete")
    BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/database_manage/{id}/detail")
    BusinessData<DatabaseManageResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/list")
    BusinessData<List<DatabaseManageResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseManageRequestDTO listDatabaseManageRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/page")
    BusinessData<PageResponseData<DatabaseManageResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO
    );

    @ApiOperation(value = "初始化数据库", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/database_manage/init_database")
    BusinessData<DatabaseManageResponseDTO> initDatabase(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "id", required = false) String id
    );
}
