package com.wondernect.stars.user.server.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.server.service.LocalUserExcelExportService;
import com.wondernect.stars.user.server.service.LocalUserExcelImportService;
import com.wondernect.stars.user.server.service.LocalUserExcelInitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: LocalUserExcelController
 * Author: chenxun
 * Date: 2020/11/26 10:36
 * Description:
 */
@Api(tags = "本地用户导入&导出", description = "本地用户导入&导出")
@Validated
@RestController
@RequestMapping(value = "/v1/wondernect/user")
public class LocalUserExcelController {

    @Autowired
    private LocalUserExcelInitService localUserExcelInitService;

    @Autowired
    private LocalUserExcelExportService localUserExcelExportService;

    @Autowired
    private LocalUserExcelImportService localUserExcelImportService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @AuthorizeServer
    @ApiOperation(value = "初始化本地用户导入导出item", httpMethod = "POST")
    @PostMapping(value = "/init_local_user_item")
    public BusinessData initLocalUserExcelItem(
            @ApiParam(required = false) @RequestParam(value = "force_update", required = false) Boolean forceUpdate
    ) {
        if (ESObjectUtils.isNull(forceUpdate)) {
            forceUpdate = false;
        }
        localUserExcelInitService.initLocalUserExcelItem(forceUpdate);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "本地用户导出", httpMethod = "POST")
    @PostMapping(value = "/excel_data_export")
    public void excelDataExport(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId,
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListUserRequestDTO listUserRequestDTO
    ) {
        try {
            localUserExcelExportService.excelDataExport(templateId, listUserRequestDTO, request, response);
        } catch (Exception e) {
            BusinessData.error(e.getMessage(), response);
        }
    }

    @AuthorizeServer
    @ApiOperation(value = "本地用户导入", httpMethod = "POST")
    @PostMapping(value = "/excel_data_import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void excelDataImport(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId,
            @ApiParam(required = true) @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            localUserExcelImportService.excelDataImport(templateId, file.getInputStream(), request, response);
        } catch (Exception e) {
            BusinessData.error(e.getMessage(), response);
        }
    }

    @AuthorizeServer
    @ApiOperation(value = "本地用户导入模板下载", httpMethod = "GET")
    @GetMapping(value = "/excel_data_import_model")
    public void excelDataImportModel(
            @ApiParam(required = true) @NotBlank(message = "模板id不能为空") @RequestParam(value = "template_id", required = false) String templateId
    ) {
        try {
            localUserExcelExportService.excelDataImportModel(templateId, request, response);
        } catch (Exception e) {
            BusinessData.error(e.getMessage(), response);
        }
    }
}
