package com.wondernect.stars.user.dao;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.elements.rdb.config.AppFilter;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    @AppFilter
    @Transactional
    public User findByMobile(String mobile) {
        User user;
        try {
            user = userRepository.findByMobile(mobile);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }

    @AppFilter
    @Transactional
    public User findByEmail(String email) {
        User user;
        try {
            user = userRepository.findByEmail(email);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }

    @AppFilter
    @Transactional
    public User findByUsername(String username) {
        User user;
        try {
            user = userRepository.findByUsername(username);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return user;
    }
}
