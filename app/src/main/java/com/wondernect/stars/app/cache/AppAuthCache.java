package com.wondernect.stars.app.cache;

import com.wondernect.elements.redis.base.HashRedisCache;
import com.wondernect.elements.redis.config.RedisConfigProperties;
import com.wondernect.stars.app.model.AppAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class AppAuthCache extends HashRedisCache<AppAuth> {

    @Autowired
    private RedisConfigProperties redisConfigProperties;

    private final static String APP_AUTH_REDIS_HASH_KEY = "app_auth";

    public void save(AppAuth appAuth) {
        super.put(APP_AUTH_REDIS_HASH_KEY, appAuth.getAppId() + redisConfigProperties.getKeySeparator() + appAuth.getUserId(), appAuth);
    }

    public void remove(String appId, String userId) {
        super.delete(APP_AUTH_REDIS_HASH_KEY, appId + redisConfigProperties.getKeySeparator() + userId);
    }

    public AppAuth get(String appId, String userId) {
        return super.get(APP_AUTH_REDIS_HASH_KEY, appId + redisConfigProperties.getKeySeparator() + userId);
    }
}
