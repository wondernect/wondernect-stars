package com.wondernect.stars.file.feign.path;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.dto.*;
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
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public LocalFilePathResponseDTO update(String id, SaveLocalFilePathRequestDTO saveLocalFilePathRequestDTO) {
        BusinessData<LocalFilePathResponseDTO> businessData = localFilePathFeignClient.update(id, saveLocalFilePathRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void delete(String id) {
        BusinessData businessData = localFilePathFeignClient.delete(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public LocalFilePathResponseDTO detail(String id) {
        BusinessData<LocalFilePathResponseDTO> businessData = localFilePathFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public LocalFilePathResponseDTO root() {
        BusinessData<LocalFilePathResponseDTO> businessData = localFilePathFeignClient.root();
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public List<LocalFilePathResponseDTO> list(ListLocalFilePathRequestDTO listLocalFilePathRequestDTO) {
        BusinessData<List<LocalFilePathResponseDTO>> businessData = localFilePathFeignClient.list(listLocalFilePathRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<LocalFilePathResponseDTO> page(PageLocalFilePathRequestDTO pageLocalFilePathRequestDTO) {
        BusinessData<PageResponseData<LocalFilePathResponseDTO>> businessData = localFilePathFeignClient.page(pageLocalFilePathRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public LocalFilePathTreeResponseDTO tree(String rootFilePathId) {
        BusinessData<LocalFilePathTreeResponseDTO> businessData = localFilePathFeignClient.tree(rootFilePathId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
