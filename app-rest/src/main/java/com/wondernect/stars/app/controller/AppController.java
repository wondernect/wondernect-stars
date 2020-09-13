package com.wondernect.stars.app.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeRoleType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeUserRole;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.ESExcelUtils;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.AppResponseDTO;
import com.wondernect.stars.app.dto.ListAppRequestDTO;
import com.wondernect.stars.app.dto.PageAppRequestDTO;
import com.wondernect.stars.app.dto.SaveAppRequestDTO;
import com.wondernect.stars.app.service.AppService;
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
 * 应用接口
 *
 * @author chenxun 2020-09-13 23:02:01
 **/
@RequestMapping(value = "/v1/wondernect/app")
@RestController
@Validated
@Api(tags = "应用接口")
public class AppController {

    @Autowired
    private AppService appService;

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<AppResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveAppRequestDTO saveAppRequestDTO
    ) {
        return new BusinessData<>(appService.create(saveAppRequestDTO));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<AppResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveAppRequestDTO saveAppRequestDTO
    ) {
        return new BusinessData<>(appService.update(id, saveAppRequestDTO));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        appService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<AppResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(appService.findById(id));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<AppResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListAppRequestDTO listAppRequestDTO
    ) {
        return new BusinessData<>(appService.list(listAppRequestDTO));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<AppResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageAppRequestDTO pageAppRequestDTO
    ) {
        return new BusinessData<>(appService.page(pageAppRequestDTO));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "获取excel的所有可用列名、类型、描述、get方法、set方法", httpMethod = "GET")
    @GetMapping(value = "/excel_item_list")
    public BusinessData<List<ESExcelItem>> excelItemList() {
        return new BusinessData<>(appService.excelItemList());
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "excel导出", httpMethod = "POST")
    @PostMapping(value = "/excel_data_export")
    public void excelDataExport(
            @ApiParam(required = true) @NotBlank(message = "excel导出服务id不能为空") @RequestParam(value = "export_service_identifier", required = false) String exportServiceIdentifier,
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListAppRequestDTO listAppRequestDTO,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        appService.excelDataExport(exportServiceIdentifier, listAppRequestDTO, request, response);
    }
}