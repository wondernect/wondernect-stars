package com.wondernect.stars.file.server.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
import com.wondernect.stars.file.server.config.FileConfigProperties;
import com.wondernect.stars.file.service.localfilepath.LocalFilePathService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RoleTypeController
 * Author: chenxun
 * Date: 2020-02-21 14:07
 * Description:
 */
@RestController
@RequestMapping(value = "/v1/wondernect/file/path")
@Validated
@Api(tags = "本地文件存储路径", description = "本地文件存储路径")
public class LocalFilePathController {

    @Autowired
    private FileConfigProperties fileConfigProperties;

    @Autowired
    private LocalFilePathService localFilePathService;

    @AuthorizeServer
    @ApiOperation(value = "创建", httpMethod = "POST")
    @PostMapping(value = "/create")
    public BusinessData<LocalFilePathResponseDTO> create(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO
    ) {
        if (ESStringUtils.isBlank(saveLocalFilePathRequestDTO.getParentPathId())) {
            saveLocalFilePathRequestDTO.setParentPathId(fileConfigProperties.getRootFilePathId());
        }
        return new BusinessData<>(localFilePathService.create(saveLocalFilePathRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "更新", httpMethod = "POST")
    @PostMapping(value = "/{id}/update")
    public BusinessData<LocalFilePathResponseDTO> update(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id,
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO
    ) {
        if (ESStringUtils.isBlank(saveLocalFilePathRequestDTO.getParentPathId())) {
            saveLocalFilePathRequestDTO.setParentPathId(fileConfigProperties.getRootFilePathId());
        }
        return new BusinessData<>(localFilePathService.update(id, saveLocalFilePathRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData delete(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        localFilePathService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<LocalFilePathResponseDTO> get(
            @ApiParam(required = true) @NotBlank(message = "请求参数不能为空") @PathVariable(value = "id", required = false) String id
    ) {
        return new BusinessData<>(localFilePathService.findById(id));
    }

    @AuthorizeServer
    @ApiOperation(value = "获取根节点", httpMethod = "GET")
    @GetMapping(value = "/root")
    public BusinessData<LocalFilePathResponseDTO> root() {
        ListLocalFilePathRequestDTO listLocalFilePathRequestDTO = new ListLocalFilePathRequestDTO();
        listLocalFilePathRequestDTO.setParentPathId(fileConfigProperties.getRootFilePathId());
        listLocalFilePathRequestDTO.setIsDeleted(false);
        List<LocalFilePathResponseDTO> localFilePathResponseDTOList = localFilePathService.list(listLocalFilePathRequestDTO);
        if (CollectionUtils.isEmpty(localFilePathResponseDTOList)) {
            return new BusinessData<>(BusinessError.SUCCESS);
        }
        return new BusinessData<>(localFilePathResponseDTOList.get(0));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<LocalFilePathResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) ListLocalFilePathRequestDTO listLocalFilePathRequestDTO
    ) {
        if (ESStringUtils.isBlank(listLocalFilePathRequestDTO.getParentPathId())) {
            listLocalFilePathRequestDTO.setParentPathId(fileConfigProperties.getRootFilePathId());
        }
        return new BusinessData<>(localFilePathService.list(listLocalFilePathRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<LocalFilePathResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "请求参数不能为空") @Validated @RequestBody(required = false) PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO
    ) {
        if (ESStringUtils.isBlank(pageLocalFilePathRequestDTO.getParentPathId())) {
            pageLocalFilePathRequestDTO.setParentPathId(fileConfigProperties.getRootFilePathId());
        }
        return new BusinessData<>(localFilePathService.page(pageLocalFilePathRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "树形结构", httpMethod = "GET")
    @GetMapping(value = "/{root_file_path_id}/tree")
    public BusinessData<LocalFilePathTreeResponseDTO> tree(
            @ApiParam(required = true) @NotBlank(message = "根节点文件路径不能为空") @PathVariable(value = "root_file_path_id", required = false) String rootFilePathId
    ) {
        return new BusinessData<>(localFilePathService.tree(rootFilePathId));
    }
}
