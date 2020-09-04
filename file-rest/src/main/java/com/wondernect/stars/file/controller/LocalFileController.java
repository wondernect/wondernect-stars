package com.wondernect.stars.file.controller;

import com.wondernect.elements.authorize.context.interceptor.AuthorizeRoleType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeServer;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeType;
import com.wondernect.elements.authorize.context.interceptor.AuthorizeUserRole;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.FileResponseDTO;
import com.wondernect.stars.file.dto.ListFileRequestDTO;
import com.wondernect.stars.file.dto.LocalFilePathResponseDTO;
import com.wondernect.stars.file.dto.PageFileRequestDTO;
import com.wondernect.stars.file.service.localfilepath.LocalFilePathService;
import com.wondernect.stars.file.service.local.LocalFileService;
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
@RequestMapping(value = "/v1/wondernect/file/local")
@Validated
@Api(tags = "本地文件服务", description = "本地文件服务")
public class LocalFileController {

    @Autowired
    private LocalFileService localFileService;

    @Autowired
    private LocalFilePathService localFilePathService;

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping(value = "/upload")
    public BusinessData<FileResponseDTO> upload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = true) @NotBlank(message = "文件存储路径id不能为空") @RequestParam(value = "path_id", required = false) String pathId,
            @ApiParam(required = true) @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        LocalFilePathResponseDTO localFilePathResponseDTO = localFilePathService.findById(pathId);
        if (ESObjectUtils.isNull(localFilePathResponseDTO)) {
            throw new BusinessException("文件存储路径不存在");
        }
        if (ESStringUtils.isBlank(localFilePathResponseDTO.getSubFilePath())) {
            throw new BusinessException("文件存储路径为空");
        }
        return new BusinessData<>(localFileService.upload(file, localFilePathResponseDTO.getSubFilePath(), fileType, new HashMap<>()));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "上传文件(微信小程序)", httpMethod = "POST")
    @PostMapping(value = "/wechat/upload")
    public BusinessData<FileResponseDTO> wechatUpload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = true) @NotBlank(message = "文件存储路径id不能为空") @RequestParam(value = "path_id", required = false) String pathId,
            @ApiParam(required = true) @NotBlank(message = "文件获取标识不能为空") @RequestParam(value = "file_key", required = false) String fileKey,
            HttpServletRequest httpServletRequest
    ) {
        LocalFilePathResponseDTO localFilePathResponseDTO = localFilePathService.findById(pathId);
        if (ESObjectUtils.isNull(localFilePathResponseDTO)) {
            throw new BusinessException("文件存储路径不存在");
        }
        if (ESStringUtils.isBlank(localFilePathResponseDTO.getSubFilePath())) {
            throw new BusinessException("文件存储路径为空");
        }
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) httpServletRequest;
        MultipartFile file = multipartHttpServletRequest.getFile(fileKey);
        return new BusinessData<>(localFileService.upload(file, localFilePathResponseDTO.getSubFilePath(), fileType, new HashMap<>()));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "删除文件", httpMethod = "POST")
    @PostMapping(value = "/{id}/delete")
    public BusinessData deleteById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    ) {
        localFileService.deleteById(id);
        return new BusinessData(BusinessError.SUCCESS);
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "获取文件信息", httpMethod = "GET")
    @GetMapping(value = "/{id}/detail")
    public BusinessData<FileResponseDTO> getById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    ) {
        return new BusinessData<>(localFileService.findById(id));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/list")
    public BusinessData<List<FileResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @RequestBody(required = false) ListFileRequestDTO listFileRequestDTO
    ) {
        return new BusinessData<>(localFileService.list(listFileRequestDTO));
    }

    @AuthorizeServer
    @AuthorizeUserRole(authorizeType = AuthorizeType.EXPIRES_TOKEN, authorizeRoleType = AuthorizeRoleType.ONLY_AUTHORIZE)
    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/page")
    public BusinessData<PageResponseData<FileResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @RequestBody(required = false) PageFileRequestDTO pageFileRequestDTO
    ) {
        return new BusinessData<>(localFileService.page(pageFileRequestDTO));
    }
}
