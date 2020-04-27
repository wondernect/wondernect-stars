package com.wondernect.stars.session.cache;

import com.wondernect.elements.redis.base.HashRedisCache;
import com.wondernect.stars.session.model.TokenSession;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/7.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class TokenSessionCache extends HashRedisCache<TokenSession> {

    private final static String TOKEN_SESSION_REDIS_HASH_KEY = "token_session";

    public void save(TokenSession tokenSession) {
        super.put(TOKEN_SESSION_REDIS_HASH_KEY, tokenSession.getToken(), tokenSession);
    }

    public void remove(String token) {
        super.delete(TOKEN_SESSION_REDIS_HASH_KEY, token);
    }

    public TokenSession get(String token) {
        return super.get(TOKEN_SESSION_REDIS_HASH_KEY, token);
    }
}
