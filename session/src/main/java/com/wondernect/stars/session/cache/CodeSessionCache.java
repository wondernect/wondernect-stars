package com.wondernect.stars.session.cache;

import com.wondernect.elements.redis.base.StringRedisCache;
import com.wondernect.elements.redis.config.RedisConfigProperties;
import com.wondernect.stars.session.model.CodeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created on 2018/4/7.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class CodeSessionCache extends StringRedisCache<CodeSession> {

    private final static String CODE_SESSION_REDIS_STRING_KEY = "code_session";

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    public void save(CodeSession codeSession) {
        super.set(CODE_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + codeSession.getCode(), codeSession, codeSession.getExpires(), TimeUnit.SECONDS);
    }

    public void saveExpire(CodeSession codeSession) {
        super.set(CODE_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + codeSession.getCode(), codeSession, codeSession.getExpires(), TimeUnit.SECONDS);
    }

    public void remove(String code) {
        super.delete(CODE_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + code);
    }

    @Override
    public CodeSession get(String code) {
        return super.get(CODE_SESSION_REDIS_STRING_KEY + redisConfigProperties.getKeySeparator() + code);
    }
}
