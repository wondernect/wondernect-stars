package com.wondernect.stars.user.cache;

import com.wondernect.elements.redis.base.HashRedisCache;
import com.wondernect.stars.user.model.OpenUser;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class OpenUserCache extends HashRedisCache<OpenUser> {

    private final static String APP_USER_REDIS_HASH_KEY = "open_user";

    public void save(OpenUser openUser) {
        super.put(APP_USER_REDIS_HASH_KEY, openUser.getUserId(), openUser);
    }

    public void remove(String userId) {
        super.delete(APP_USER_REDIS_HASH_KEY, userId);
    }

    public OpenUser get(String userId) {
        return super.get(APP_USER_REDIS_HASH_KEY, userId);
    }
}
