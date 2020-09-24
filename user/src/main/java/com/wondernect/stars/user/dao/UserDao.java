package com.wondernect.stars.user.dao;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created on 2018/4/4.
 * wondernect.com
 *
 * @author sunbeam
 */
@Repository
public class UserDao extends BaseStringDao<User> {

    @Autowired
    private UserRepository userRepository;

    public User findByMobile(String mobile, String appId) {
        User user;
        try {
            user = userRepository.findByMobileAndCreateApp(mobile, appId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }

    public User findByEmail(String email, String appId) {
        User user;
        try {
            user = userRepository.findByEmailAndCreateApp(email, appId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }

    public User findByUsername(String username, String appId) {
        User user;
        try {
            user = userRepository.findByUsernameAndCreateApp(username, appId);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }
}
