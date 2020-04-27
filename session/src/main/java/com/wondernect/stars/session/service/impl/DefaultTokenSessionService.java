package com.wondernect.stars.session.service.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.session.common.error.SessionErrorEnum;
import com.wondernect.stars.session.common.exception.SessionException;
import com.wondernect.stars.session.manager.TokenSessionManager;
import com.wondernect.stars.session.model.TokenSession;
import com.wondernect.stars.session.service.TokenSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultTokenSessionService
 * Author: chenxun
 * Date: 2019/4/4 10:22
 * Description:
 */
@Service
public class DefaultTokenSessionService implements TokenSessionService {

    @Autowired
    private TokenSessionManager tokenSessionManager;

    public TokenSession requestTokenSession(String description, String ip, String devicePlatform, String device, String deviceIdentifier) {
        return tokenSessionManager.save(
                new TokenSession(
                        ip,
                        devicePlatform,
                        device,
                        deviceIdentifier,
                        description
                )
        );
    }

    public void deleteByToken(String userId, String token) {
        TokenSession tokenSession = tokenSessionManager.findByToken(token);
        if (ESObjectUtils.isNotNull(tokenSession)) {
            if (!tokenSession.getCreateUser().equalsIgnoreCase(userId)) {
                throw new SessionException(SessionErrorEnum.TOKEN_SESSION_USER_INVALID);
            }
            tokenSessionManager.deleteByToken(token);
        }
    }

    public TokenSession authTokenSession(String token) {
        TokenSession tokenSession = tokenSessionManager.findByToken(token);
        if (ESObjectUtils.isNull(tokenSession)) {
            throw new SessionException(SessionErrorEnum.TOKEN_SESSION_NOT_FOUND);
        }
        return tokenSession;
    }
}
