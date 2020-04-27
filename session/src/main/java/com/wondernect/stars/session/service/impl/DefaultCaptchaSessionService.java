package com.wondernect.stars.session.service.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRandomUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.config.SessionConfigProperties;
import com.wondernect.stars.session.manager.CaptchaSessionManager;
import com.wondernect.stars.session.model.CaptchaSession;
import com.wondernect.stars.session.service.CaptchaSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultCaptchaSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
@Service
public class DefaultCaptchaSessionService implements CaptchaSessionService {

    @Autowired
    private SessionConfigProperties sessionConfigProperties;

    @Autowired
    private CaptchaSessionManager captchaSessionManager;

    public CaptchaSession requestCaptchaSession(String username, String description, Long expires, String ip, String devicePlatform, String device) {
        if (ESObjectUtils.isNull(expires) || expires == 0L) {
            expires = sessionConfigProperties.getCaptchaExpires();
        }
        CaptchaSession captchaSession;
        // captchaType 验证码类型: 0-数字; 1-字符;
        if (sessionConfigProperties.getCaptchaType() == 1) {
            captchaSession = captchaSessionManager.save(
                    new CaptchaSession(
                            username,
                            ESRandomUtils.randomStringWithChars(sessionConfigProperties.getCaptchaLength()),
                            "",
                            "",
                            ip,
                            devicePlatform,
                            device,
                            description,
                            expires
                    )
            );
        } else {
            captchaSession = captchaSessionManager.save(
                    new CaptchaSession(
                            username,
                            ESRandomUtils.randomNumberString(sessionConfigProperties.getCaptchaLength()),
                            "",
                            "",
                            ip,
                            devicePlatform,
                            device,
                            description,
                            expires
                    )
            );
        }
        return captchaSession;
    }

    public void deleteByCaptchaSessionId(String username, String captchaSessionId) {
        CaptchaSession captchaSession = captchaSessionManager.findById(captchaSessionId);
        if (ESObjectUtils.isNotNull(captchaSession)) {
            if (ESStringUtils.isNotBlank(username) &&
                    !ESStringUtils.equalsIgnoreCase(username, captchaSession.getUsername())) {
                throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_USERNAME_INVALID);
            }
            captchaSessionManager.deleteCacheById(captchaSessionId);
        }
    }

    public CaptchaSession authCaptchaSession(String username, String captchaSessionId, String captcha) {
        CaptchaSession captchaSession = captchaSessionManager.findCacheById(captchaSessionId);
        if (ESObjectUtils.isNull(captchaSession)) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_NOT_FOUND);
        }
        if (!captcha.equalsIgnoreCase(captchaSession.getCaptcha())) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_CAPTCHA_INVALID);
        }
        if (ESStringUtils.isNotBlank(username) && !username.equalsIgnoreCase(captchaSession.getUsername())) {
            throw new SessionException(SessionErrorEnum.CAPTCHA_SESSION_USERNAME_INVALID);
        }
        return captchaSession;
    }
}
