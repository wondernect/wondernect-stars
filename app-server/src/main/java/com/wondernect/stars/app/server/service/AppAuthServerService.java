package com.wondernect.stars.app.server.service;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.app.dto.auth.AppAuthResponseDTO;
import com.wondernect.stars.app.model.AppAuth;
import com.wondernect.stars.app.service.AppAuthService;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.feign.user.UserServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: AppAuthServerService
 * Author: chenxun
 * Date: 2020/12/29 17:58
 * Description:
 */
@Service
public class AppAuthServerService extends AppAuthService {

    @Autowired
    private UserServerService userServerService;

    @Override
    public AppAuthResponseDTO generate(AppAuth appAuth) {
        AppAuthResponseDTO appAuthResponseDTO = super.generate(appAuth);
        UserResponseDTO userResponseDTO = userServerService.detail(appAuthResponseDTO.getUserId());
        appAuthResponseDTO.setUserName(ESObjectUtils.isNotNull(userResponseDTO) ? userResponseDTO.getName() : null);
        return appAuthResponseDTO;
    }
}
