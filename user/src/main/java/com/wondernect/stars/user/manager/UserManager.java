package com.wondernect.stars.user.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.user.cache.UserCache;
import com.wondernect.stars.user.dao.UserDao;
import com.wondernect.stars.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class UserManager extends BaseStringManager<User> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserCache userCache;

    @Override
    public User save(User user) {
        User userSave = userDao.save(user);
        userCache.save(userSave);
        return userSave;
    }

    public void deleteById(String userId) {
        userCache.remove(userId);
        userDao.deleteById(userId);
    }

    @Override
    public User findById(String userId) {
        User user = userCache.get(userId);
        if (ESObjectUtils.isNull(user)) {
            user = userDao.findById(userId);
            if (ESObjectUtils.isNotNull(user)) {
                userCache.save(user);
            }
        }
        return user;
    }

    public User findByMobile(String mobile) {
        return userDao.findByMobile(mobile);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
