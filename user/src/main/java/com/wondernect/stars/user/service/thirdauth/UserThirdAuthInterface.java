package com.wondernect.stars.user.service.thirdauth;

import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.em.AppType;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: InitUserThirdAuthService
 * Author: chenxun
 * Date: 2020-06-25 17:41
 * Description:
 */
public interface UserThirdAuthInterface {

    /**
     * 创建（绑定用户第三方信息）
     */
    UserThirdAuthResponseDTO create(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO);

    /**
     * 更新
     */
    UserThirdAuthResponseDTO update(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO);

    /**
     * 删除
     */
    void deleteByUserIdAndAppType(String userId, AppType appType);

    /**
     * 删除
     */
    void deleteByUserId(String userId);

    /**
     * 获取
     */
    UserThirdAuthResponseDTO findByUserIdAndAppType(String userId, AppType appType);

    /**
     * 获取
     */
    UserThirdAuthResponseDTO findByAppTypeAndAppUserId(AppType appType, String appUserId);
}
