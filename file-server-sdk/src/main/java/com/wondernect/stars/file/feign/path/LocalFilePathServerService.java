package com.wondernect.stars.file.feign.path;

import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.ListLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.LocalFilePathResponseDTO;
import com.wondernect.stars.file.dto.PageLocalFilePathRequestDTO;
import com.wondernect.stars.file.dto.SaveLocalFilePathRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: LocalFilePathServerService
 * Author: chenxun
 * Date: 2020-08-06 15:55
 * Description: local file path feign service
 */
@Service
public class LocalFilePathServerService {

    @Autowired
    private LocalFilePathFeignClient localFilePathFeignClient;

    public LocalFilePathResponseDTO create(SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO) {
        BusinessData<LocalFilePathResponseDTO> businessData = localFilePathFeignClient.create(saveLocalFilePathRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public LocalFilePathResponseDTO get(String id) {
        BusinessData<LocalFilePathResponseDTO> businessData = localFilePathFeignClient.get(id);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO) {
        BusinessData<List<LocalFilePathResponseDTO>> businessData = localFilePathFeignClient.list(listLocalFilePathRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }

    public PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO) {
        BusinessData<PageResponseData<LocalFilePathResponseDTO>> businessData = localFilePathFeignClient.page(pageLocalFilePathRequestDTO);
        if (!businessData.success()) {
            return null;
        }
        return businessData.getData();
    }
}
