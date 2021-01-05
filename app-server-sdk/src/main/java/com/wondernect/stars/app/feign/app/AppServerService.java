package com.wondernect.stars.app.feign.app;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.AppResponseDTO;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.dto.ListAppRequestDTO;
import com.wondernect.stars.app.dto.PageAppRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppServerService
 * Author: chenxun
 * Date: 2020-09-13 23:25
 * Description:
 */
@Service
public class AppServerService {

    @Autowired
    private AppFeignClient appFeignClient;

    public AppResponseDTO detail(String id) {
        BusinessData<AppResponseDTO> businessData = appFeignClient.detail(id);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public void auth(AuthAppRequestDTO authAppRequestDTO) {
        BusinessData businessData = appFeignClient.auth(authAppRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
    }

    public List<AppResponseDTO> list(ListAppRequestDTO listAppRequestDTO) {
        BusinessData<List<AppResponseDTO>> businessData = appFeignClient.list(listAppRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }

    public PageResponseData<AppResponseDTO> page(PageAppRequestDTO pageAppRequestDTO) {
        BusinessData<PageResponseData<AppResponseDTO>> businessData = appFeignClient.page(pageAppRequestDTO);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
