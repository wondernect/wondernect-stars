package com.wondernect.stars.user.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.user.cache.UserCache;
import com.wondernect.stars.user.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/4/4.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class UserManager extends BaseStringManager<User> {

    @Autowired
    private UserCache userCache;

    @Override
    public User save(User user) {
        User userSave = super.save(user);
        userCache.save(userSave);
        return userSave;
    }

    public void deleteById(String userId) {
        userCache.remove(userId);
        super.deleteById(userId);
    }

    @Override
    public User findById(String userId) {
        User user = userCache.get(userId);
        if (ESObjectUtils.isNull(user)) {
            user = super.findById(userId);
            if (ESObjectUtils.isNotNull(user)) {
                userCache.save(user);
            }
        }
        return user;
    }

    public User findByMobile(String mobile) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("mobile", mobile));
        List<User> userList = super.findAll(userCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public User findByEmail(String email) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("email", email));
        List<User> userList = super.findAll(userCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    public User findByUsername(String username) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("username", username));
        List<User> userList = super.findAll(userCriteria, new ArrayList<>());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }
}
