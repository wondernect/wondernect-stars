package com.wondernect.stars.file.feign.path;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.ListLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.LocalFilePathResponseDTO;
import com.wondernect.stars.file.dto.PageLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.SaveLocalFilePathRequestDTO;
import com.wondernect.stars.file.feign.config.WondernectFileFeignConfiguration;
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
@FeignClient(name = "${wondernect.stars.file.feign.name}", url = "${wondernect.stars.file.feign.url}", configuration = WondernectFileFeignConfiguration.class)
public interface LocalFilePathFeignClient {

    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/path/create")
    public BusinessData<LocalFilePathResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO
    );

    @ApiOperation(value = "获取", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/file/path/{id}/detail")
    public BusinessData<LocalFilePathResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取根节点", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/file/path/root")
    public BusinessData<LocalFilePathResponseDTO> root();

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/path/list")
    public BusinessData<List<LocalFilePathResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListLocalFilePathRequestDTO listLocalFilePathRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/path/page")
    public BusinessData<PageResponseData<LocalFilePathResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO
    );
}
