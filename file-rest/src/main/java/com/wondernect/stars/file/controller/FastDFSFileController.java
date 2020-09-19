package com.wondernect.stars.file.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import com.wondernect.stars.file.service.fastdfs.FastDFSFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: LocalFileController
 * Author: chenxun
 * Date: 2019/3/28 16:12
 * Description: local file controller
 */
@RestController
@RequestMapping(value = "/v1/wondernect/file/fast_dfs")
@Validated
@Api(tags = "FastDFS文件服务", description = "FastDFS文件服务")
public class FastDFSFileController {

    @Autowired
    private FastDFSFileService fastDFSFileService;

    @AuthorizeServer
    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping(value = "/upload")
    public BusinessData<FileResponseDTO> upload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = true) @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        return new BusinessData<>(fastDFSFileService.upload(file, "", fileType, new HashMap<>()));
    }

    @AuthorizeServer
    @ApiOperation(value = "上传文件(微信小程序)", httpMethod = "POST")
    @PostMapping(value = "/wechat/upload")
    public BusinessData<FileResponseDTO> wechatUpload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = false) @NotBlank(message = "文件获取标识不能为空") @RequestParam(value = "file_key", required = false) String fileKey,
            HttpServletRequest httpServletRequest
    ) {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
        MultipartFile file = multipartHttpServletRequest.getFile(fileKey);
        return new BusinessData<>(fastDFSFileService.upload(file, "", fileType, new HashMap<>()));
    }

    @AuthorizeServer
    @ApiOperation(value = "删除文件", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData deleteById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    ) {
        fastDFSFileService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @ApiOperation(value = "获取文件信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<FileResponseDTO> getById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    ) {
        return new BusinessData<>(fastDFSFileService.findById(id));
    }

    @AuthorizeServer
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<FileResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @RequestBody(required = false) ListFileRequestDTO listFileRequestDTO
    ) {
        return new BusinessData<>(fastDFSFileService.list(listFileRequestDTO));
    }

    @AuthorizeServer
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<FileResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @RequestBody(required = false) PageFileRequestDTO pageFileRequestDTO
    ) {
        return new BusinessData<>(fastDFSFileService.page(pageFileRequestDTO));
    }
}
