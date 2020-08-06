package com.wondernect.stars.file.feign.local;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: LocalFilePathServerService
 * Author: chenxun
 * Date: 2020-08-06 15:55
 * Description: local file path feign service
 */
@Service
public class LocalFileServerService {

    @Autowired
    private LocalFileFeignClient localFileFeignClient;

    public FileResponseDTO upload(String fileType, String pathId, MultipartFile file) {
        BusinessData<FileResponseDTO> businessData = localFileFeignClient.upload(fileType, pathId, file);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public FileResponseDTO wechatUpload(String fileType, String pathId, String fileKey, HttpServletRequest httpServletRequest) {
        BusinessData<FileResponseDTO> businessData = localFileFeignClient.wechatUpload(fileType, pathId, fileKey, httpServletRequest);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean deleteById(String id) {
        BusinessData businessData = localFileFeignClient.deleteById(id);
        return businessData.success();
    }

    public FileResponseDTO getById(String id) {
        BusinessData<FileResponseDTO> businessData = localFileFeignClient.getById(id);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO) {
        BusinessData<List<FileResponseDTO>> businessData = localFileFeignClient.list(listFileRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO) {
        BusinessData<PageResponseData<FileResponseDTO>> businessData = localFileFeignClient.page(pageFileRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
