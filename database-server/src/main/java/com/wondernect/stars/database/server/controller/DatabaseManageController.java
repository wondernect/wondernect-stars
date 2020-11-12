package com.wondernect.stars.database.server.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.database.dto.DatabaseManageResponseDTO;
import com.wondernect.stars.database.dto.ListDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.PageDatabaseManageRequestDTO;
import com.wondernect.stars.database.dto.SaveDatabaseManageRequestDTO;
import com.wondernect.stars.database.service.DatabaseManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 数据库接口
 *
 * @author liyafei 2020-11-09 15:57:45
 **/
@RequestMapping(value = "/v1/wondernect/database_manage")
@RestController
@Validated
@Api(tags = "数据库接口")
public class DatabaseManageController {

    @Autowired
    private DatabaseManageService databaseManageService;

    @AuthorizeServer
    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<DatabaseManageResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO
    ) {
        return new BusinessData<>(databaseManageService.create(saveDatabaseManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<DatabaseManageResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveDatabaseManageRequestDTO saveDatabaseManageRequestDTO
    ) {
        return new BusinessData<>(databaseManageService.update(id, saveDatabaseManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        databaseManageService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<DatabaseManageResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(databaseManageService.findById(id));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<DatabaseManageResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseManageRequestDTO listDatabaseManageRequestDTO
    ) {
        return new BusinessData<>(databaseManageService.list(listDatabaseManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<DatabaseManageResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageDatabaseManageRequestDTO pageDatabaseManageRequestDTO
    ) {
        return new BusinessData<>(databaseManageService.page(pageDatabaseManageRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "获取excel的所有可用列名、类型、描述、get方法、set方法", httpMethod = "GET")
    @GetMapping(value = "/excel_item_list")
    public BusinessData<List<ESExcelItem>> excelItemList() {
        return new BusinessData<>(databaseManageService.excelItemList());
    }

    @AuthorizeServer
    @ApiOperation(value = "excel导出", httpMethod = "POST")
    @PostMapping(value = "/excel_data_export")
    public void excelDataExport(
            @ApiParam(required = true) @NotBlank(message = "excel导出服务id不能为空") @RequestParam(value = "export_service_identifier", required = false) String exportServiceIdentifier,
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListDatabaseManageRequestDTO listDatabaseManageRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        databaseManageService.excelDataExport(exportServiceIdentifier, listDatabaseManageRequestDTO, request, response);
    }

    @AuthorizeServer
    @ApiOperation(value = "初始化数据库", httpMethod = "POST")
    @PostMapping(value = "/init_database")
    public BusinessData<DatabaseManageResponseDTO> initDatabase(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @RequestParam(value = "id", required = false) String id
    ){
        return new BusinessData<>(databaseManageService.initDatabase(id));
    }
}