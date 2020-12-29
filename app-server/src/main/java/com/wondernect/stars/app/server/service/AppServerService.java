package com.wondernect.stars.app.server.service;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.app.dto.AppResponseDTO;
import com.wondernect.stars.app.model.App;
import com.wondernect.stars.app.service.AppService;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.feign.user.UserServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: UserServerService
 * Author: chenxun
 * Date: 2020/12/29 17:52
 * Description:
 */
@Service
public class AppServerService extends AppService {

    @Autowired
    private UserServerService userServerService;

    @Override
    public AppResponseDTO generate(App app) {
        AppResponseDTO appResponseDTO = super.generate(app);
        UserResponseDTO userResponseDTO = userServerService.detail(appResponseDTO.getUserId());
        appResponseDTO.setUserName(ESObjectUtils.isNotNull(userResponseDTO) ? userResponseDTO.getName() : null);
        return appResponseDTO;
    }
}
