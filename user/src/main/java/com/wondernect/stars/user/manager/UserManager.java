package com.wondernect.stars.user.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.cache.UserCache;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.repository.UserDao;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<User> findAll(String createUser, String name, String mobile, String email, List<SortData> sortDataList) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(
                Restrictions.and(
                        Restrictions.eq("createUser", createUser),
                        Restrictions.or(
                                Restrictions.like("name", name, MatchMode.ANYWHERE),
                                Restrictions.like("mobile", mobile, MatchMode.ANYWHERE),
                                Restrictions.like("email", email, MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(userCriteria, sortDataList);
    }

    public PageResponseData<User> findAll(String createUser, String name, String mobile, String email, PageRequestData pageRequestData) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(
                Restrictions.and(
                        Restrictions.eq("createUser", createUser),
                        Restrictions.or(
                                Restrictions.like("name", name, MatchMode.ANYWHERE),
                                Restrictions.like("mobile", mobile, MatchMode.ANYWHERE),
                                Restrictions.like("email", email, MatchMode.ANYWHERE)
                        )
                )
        );
        return super.findAll(userCriteria, pageRequestData);
    }
}
