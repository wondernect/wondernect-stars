package com.wondernect.stars.session.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseTokenManager;
import com.wondernect.stars.session.cache.TokenSessionCache;
import com.wondernect.stars.session.model.TokenSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class TokenSessionManager extends BaseTokenManager<TokenSession> {

    @Autowired
    private TokenSessionCache tokenSessionCache;

    @Override
    public TokenSession save(TokenSession tokenSession) {
        TokenSession tokenSessionCreate = super.save(tokenSession);
        tokenSessionCache.save(tokenSessionCreate);
        return tokenSessionCreate;
    }

    @Override
    public void deleteByToken(String token) {
        tokenSessionCache.remove(token);
        super.deleteByToken(token);
    }

    @Override
    public TokenSession findByToken(String token) {
        TokenSession tokenSession = tokenSessionCache.get(token);
        if (ESObjectUtils.isNull(tokenSession)) {
            tokenSession = super.findByToken(token);
            if (ESObjectUtils.isNotNull(tokenSession)) {
                tokenSessionCache.save(tokenSession);
            }
        }
        return tokenSession;
    }
}
