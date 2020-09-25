package com.wondernect.stars.file.feign.fastdfs;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: LocalFilePathServerService
 * Author: chenxun
 * Date: 2020-08-06 15:55
 * Description: local file path feign service
 */
@Service
public class FastDFSServerService {

    @Autowired
    private FastDFSFileFeignClient fastDFSFileFeignClient;

    public FileResponseDTO upload(String fileType, MultipartFile file) {
        BusinessData<FileResponseDTO> businessData = fastDFSFileFeignClient.upload(fileType, file);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public boolean deleteById(String id) {
        BusinessData businessData = fastDFSFileFeignClient.deleteById(id);
        return businessData.success();
    }

    public FileResponseDTO getById(String id) {
        BusinessData<FileResponseDTO> businessData = fastDFSFileFeignClient.getById(id);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<FileResponseDTO> list(ListFileRequestDTO listFileRequestDTO) {
        BusinessData<List<FileResponseDTO>> businessData = fastDFSFileFeignClient.list(listFileRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<FileResponseDTO> page(PageFileRequestDTO pageFileRequestDTO) {
        BusinessData<PageResponseData<FileResponseDTO>> businessData = fastDFSFileFeignClient.page(pageFileRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
