package com.wondernect.stars.session.service;

import com.wondernect.stars.session.model.TokenSession;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: TokenSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface TokenSessionService {

    /**
     * 请求(缓存&数据库)
     */
    TokenSession requestTokenSession(String description, String ip, String devicePlatform, String device, String deviceIdentifier);

    /**
     * 删除(缓存&数据库)
     */
    void deleteByToken(String userId, String token);

    /**
     * 验证(缓存&数据库)
     */
    TokenSession authTokenSession(String token);
}
