package com.wondernect.stars.session.service;

import com.wondernect.stars.session.model.CodeSession;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CodeSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface CodeSessionService {

    /**
     * 请求(缓存&数据库)
     */
    CodeSession requestCodeSession(String description, Long expires, String ip, String devicePlatform, String device);

    /**
     * 续约/刷新(缓存)
     */
    CodeSession refreshCodeSession(CodeSession codeSession);

    /**
     * 删除(缓存)
     */
    void deleteByCode(String userId, String code);

    /**
     * 验证(缓存)
     */
    CodeSession authCodeSession(String code);
}
