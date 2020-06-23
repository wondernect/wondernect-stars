package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.dao.BaseStringDao;
import com.wondernect.elements.rdb.common.error.RDBErrorEnum;
import com.wondernect.elements.rdb.common.exception.RDBException;
import com.wondernect.stars.user.model.UserAuth;
import com.wondernect.stars.user.model.em.PasswordType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: RDBUserAuthDao
 * Author: chenxun
 * Date: 2018/10/24 15:57
 * Description:
 */
@Repository
public class UserAuthDao extends BaseStringDao<UserAuth> {

    @Autowired
    private UserAuthRepository userAuthRepository;

    public UserAuth findByUserIdAndPasswordType(String userId, PasswordType passwordType) {
        UserAuth userAuth;
        try {
            userAuth = userAuthRepository.findByUserIdAndPasswordType(userId, passwordType);
        } catch (RuntimeException e) {
            throw new RDBException(RDBErrorEnum.RDB_GET_FAILED);
        }
        return userAuth;
    }
}
