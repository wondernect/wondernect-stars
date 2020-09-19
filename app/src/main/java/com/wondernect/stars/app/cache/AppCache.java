package com.wondernect.stars.app.cache;

import com.wondernect.elements.redis.base.HashRedisCache;
import com.wondernect.stars.app.model.App;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class AppCache extends HashRedisCache<App> {

    private final static String APP_REDIS_HASH_KEY = "app";

    public void save(App app) {
        super.put(APP_REDIS_HASH_KEY, app.getId(), app);
    }

    public void remove(String appId) {
        super.delete(APP_REDIS_HASH_KEY, appId);
    }

    public App get(String appId) {
        return super.get(APP_REDIS_HASH_KEY, appId);
    }
}
