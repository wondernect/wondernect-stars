package com.wondernect.stars.app.feign.auth;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.response.BusinessData;
import com.wondernect.stars.app.dto.auth.AppAuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AppServerService
 * Author: chenxun
 * Date: 2020-09-13 23:25
 * Description:
 */
@Service
public class AppAuthServerService {

    @Autowired
    private AppAuthFeignClient appAuthFeignClient;

    public AppAuthResponseDTO exist(String appId, String userId) {
        BusinessData<AppAuthResponseDTO> businessData = appAuthFeignClient.exist(appId, userId);
        if (!businessData.success()) {
            throw new BusinessException(businessData);
        }
        return businessData.getData();
    }
}
