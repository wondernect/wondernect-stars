package com.wondernect.stars.office.feign.excel.param;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.dto.param.ExcelTemplateParamResponseDTO;
import com.wondernect.stars.office.excel.dto.param.ListExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.PageExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.excel.dto.param.SaveExcelTemplateParamRequestDTO;
import com.wondernect.stars.office.feign.config.WondernectOfficeFeignConfiguration;
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

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DepartmentFeignService
 * Author: chenxun
 * Date: 2019/8/1 19:37
 * Description: 部门服务
 */
@FeignClient(name = "${wondernect.stars.office.feign.name}", url = "${wondernect.stars.office.feign.url}", path = "/v1/wondernect/office/excel_template_param", configuration = WondernectOfficeFeignConfiguration.class)
public interface ExcelTemplateParamFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<ExcelTemplateParamResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO
    );

    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<ExcelTemplateParamResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveExcelTemplateParamRequestDTO saveExcelTemplateParamRequestDTO
    );

    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取详细信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<ExcelTemplateParamResponseDTO> detail(
            @ApiParam(required = true) @NotBlank(message = "对象id不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<ExcelTemplateParamResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @Validated @RequestBody(required = false) ListExcelTemplateParamRequestDTO listExcelTemplateParamRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<ExcelTemplateParamResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @Validated @RequestBody(required = false) PageExcelTemplateParamRequestDTO pageExcelTemplateParamRequestDTO
    );
}
