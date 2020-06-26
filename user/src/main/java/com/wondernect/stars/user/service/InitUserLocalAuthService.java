package com.wondernect.stars.user.service;

import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitUserLocalAuthService
 * Author: chenxun
 * Date: 2020-06-25 17:40
 * Description:
 */
public interface InitUserLocalAuthService {

    /**
     * 创建
     */
    UserLocalAuthResponseDTO create(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO);

    /**
     * 更新
     */
    UserLocalAuthResponseDTO update(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO);

    /**
     * 删除
     */
    void deleteById(String userId);

    /**
     * 获取
     */
    UserLocalAuthResponseDTO findById(String userId);

    /**
     * 认证
     */
    UserLocalAuthResponseDTO auth(String userId, AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO);
}
