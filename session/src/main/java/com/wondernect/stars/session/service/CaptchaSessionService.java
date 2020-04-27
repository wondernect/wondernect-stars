package com.wondernect.stars.session.service;

import com.wondernect.stars.session.model.CaptchaSession;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: CaptchaSessionService
 * Author: chenxun
 * Date: 2019/4/22 17:30
 * Description:
 */
public interface CaptchaSessionService {

    /**
     * 请求(缓存&数据库)
     */
    CaptchaSession requestCaptchaSession(String username, String description, Long expires, String ip, String devicePlatform, String device);

    /**
     * 删除(缓存)
     */
    void deleteByCaptchaSessionId(String username, String captchaSessionId);

    /**
     * 验证(缓存)
     */
    CaptchaSession authCaptchaSession(String username, String captchaSessionId, String captcha);
}
