package com.wondernect.stars.user.cache;

import com.wondernect.elements.redis.base.HashRedisCache;
import com.wondernect.stars.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Repository
public class UserCache extends HashRedisCache<User> {

    private final static String USER_REDIS_HASH_KEY = "user";

    public void save(User user) {
        super.put(USER_REDIS_HASH_KEY, user.getId(), user);
    }

    public void remove(String userId) {
        super.delete(USER_REDIS_HASH_KEY, userId);
    }

    public User get(String userId) {
        return super.get(USER_REDIS_HASH_KEY, userId);
    }
}
