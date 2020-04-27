package com.wondernect.stars.session.cache;

import com.wondernect.elements.redis.base.StringRedisCache;
import com.wondernect.elements.redis.config.RedisConfigProperties;
import com.wondernect.stars.session.model.CaptchaSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RedisCaptchaSessionCache
 * Author: chenxun
 * Date: 2018/12/29 9:25
 * Description:
 */
@Repository
public class CaptchaSessionCache extends StringRedisCache<CaptchaSession> {

    private final static String CAPTCHA_SESSION_REDIS_STRING_KEY = "captcha_session";

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    public void save(CaptchaSession captchaSession) {
        super.set(CAPTCHA_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + captchaSession.getId(), captchaSession, captchaSession.getExpires(), TimeUnit.SECONDS);
    }

    public void remove(String captchaSessionId) {
        super.delete(CAPTCHA_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + captchaSessionId);
    }

    public CaptchaSession get(String captchaSessionId) {
        return super.get(CAPTCHA_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + captchaSessionId);
    }
}
