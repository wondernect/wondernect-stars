package com.wondernect.stars.session.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.session.cache.CaptchaSessionCache;
import com.wondernect.stars.session.model.CaptchaSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: DefaultCaptchaSessionManager
 * Author: chenxun
 * Date: 2018/12/29 9:35
 * Description:
 */
@Service
public class CaptchaSessionManager extends BaseStringManager<CaptchaSession> {

    @Autowired
    private CaptchaSessionCache captchaSessionCache;

    @Override
    public CaptchaSession save(CaptchaSession captchaSession) {
        CaptchaSession captchaSessionCreate = super.save(captchaSession);
        captchaSessionCache.save(captchaSessionCreate);
        return captchaSessionCreate;
    }

    @Override
    public void deleteById(String captchaSessionId) {
        captchaSessionCache.remove(captchaSessionId);
        super.deleteById(captchaSessionId);
    }

    public void deleteCacheById(String captchaSessionId) {
        captchaSessionCache.remove(captchaSessionId);
    }

    @Override
    public CaptchaSession findById(String captchaSessionId) {
        CaptchaSession captchaSession = captchaSessionCache.get(captchaSessionId);
        if (ESObjectUtils.isNull(captchaSession)) {
            captchaSession = super.findById(captchaSessionId);
        }
        return captchaSession;
    }

    public CaptchaSession findCacheById(String captchaSessionId) {
        return captchaSessionCache.get(captchaSessionId);
    }
}
