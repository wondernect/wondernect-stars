package com.wondernect.stars.file.feign.fastdfs;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
import com.wondernect.stars.file.feign.config.WondernectFileFeignConfiguration;
import com.wondernect.stars.file.feign.local.LocalFileFeignClient;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@FeignClient(value = "wondernect-stars-file", configuration = {WondernectFileFeignConfiguration.class, LocalFileFeignClient.MultipartSupportConfig.class})
public interface FastDFSFileFeignClient {

    @Configuration
    class MultipartSupportConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

    @ApiOperation(value = "上传文件", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/fast_dfs/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public BusinessData<FileResponseDTO> upload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = true) @RequestPart(value = "file", required = false) MultipartFile file
    );

    @ApiOperation(value = "上传文件(微信小程序)", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/fast_dfs/wechat/upload")
    public BusinessData<FileResponseDTO> wechatUpload(
            @ApiParam(required = false, allowableValues = "IMAGE, IMAGE_FILE, VOICE, VIDEO, FILE") @NotBlank(message = "文件类型不能为空") @RequestParam(value = "file_type", required = false) String fileType,
            @ApiParam(required = false) @NotBlank(message = "文件获取标识不能为空") @RequestParam(value = "file_key", required = false) String fileKey,
            HttpServletRequest httpServletRequest
    );

    @ApiOperation(value = "删除文件", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/fast_dfs/{id}/delete")
    public BusinessData deleteById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    );

    @ApiOperation(value = "获取文件信息", httpMethod = "GET")
    @GetMapping(value = "/v1/wondernect/file/fast_dfs/{id}/detail")
    public BusinessData<FileResponseDTO> getById(
            @ApiParam(required = true) @NotBlank(message = "文件id不能为空") @RequestParam(value = "id", required = false) String id
    );

    @ApiOperation(value = "列表", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/fast_dfs/list")
    public BusinessData<List<FileResponseDTO>> list(
            @ApiParam(required = true) @NotNull(message = "列表请求参数不能为空") @RequestBody(required = false) ListFileRequestDTO listFileRequestDTO
    );

    @ApiOperation(value = "分页", httpMethod = "POST")
    @PostMapping(value = "/v1/wondernect/file/fast_dfs/page")
    public BusinessData<PageResponseData<FileResponseDTO>> page(
            @ApiParam(required = true) @NotNull(message = "分页请求参数不能为空") @RequestBody(required = false) PageFileRequestDTO pageFileRequestDTO
    );
}
