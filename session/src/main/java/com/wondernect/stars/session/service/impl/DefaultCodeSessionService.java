package com.wondernect.stars.session.service.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.config.SessionConfigProperties;
import com.wondernect.stars.session.manager.CodeSessionManager;
import com.wondernect.stars.session.model.CodeSession;
import com.wondernect.stars.session.service.CodeSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultCodeSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
@Service
public class DefaultCodeSessionService implements CodeSessionService {

    @Autowired
    private SessionConfigProperties sessionConfigProperties;

    @Autowired
    private CodeSessionManager codeSessionManager;

    public CodeSession requestCodeSession(String description, Long expires, String ip, String devicePlatform, String device) {
        if (ESObjectUtils.isNull(expires) || expires == 0L) {
            expires = sessionConfigProperties.getCodeExpires();
        }
        return codeSessionManager.save(
                new CodeSession(
                        ip,
                        devicePlatform,
                        device,
                        description,
                        expires
                )
        );
    }

    public CodeSession refreshCodeSession(CodeSession codeSession) {
        if (ESObjectUtils.isNull(codeSession)) {
            throw new SessionException(SessionErrorEnum.CODE_SESSION_NOT_FOUND);
        } else {
            codeSessionManager.saveExpireCodeCache(codeSession);
        }
        return codeSession;
    }

    public void deleteByCode(String userId, String code) {
        CodeSession codeSession = codeSessionManager.findByCode(code);
        if (ESObjectUtils.isNotNull(codeSession)) {
            if (!codeSession.getCreateUser().equalsIgnoreCase(userId)) {
                throw new SessionException(SessionErrorEnum.CODE_SESSION_USER_INVALID);
            }
            codeSessionManager.deleteCacheByCode(code);
        }
    }

    public CodeSession authCodeSession(String code) {
        CodeSession codeSession = codeSessionManager.findCacheByCode(code);
        if (ESObjectUtils.isNull(codeSession)) {
            throw new SessionException(SessionErrorEnum.CODE_SESSION_NOT_FOUND);
        }
        return codeSession;
    }
}
