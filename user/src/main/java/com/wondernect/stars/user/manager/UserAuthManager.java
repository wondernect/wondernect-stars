package com.wondernect.stars.user.manager;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.model.UserAuth;
import com.wondernect.stars.user.model.em.PasswordType;
import com.wondernect.stars.user.repository.UserAuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: UserAuthManagerImpl
 * Author: chenxun
 * Date: 2018/10/24 16:09
 * Description:
 */
@Service
public class UserAuthManager extends BaseStringManager<UserAuth> {

    @Autowired
    private UserAuthDao userAuthDao;

    public void deleteByUserIdAndPasswordType(String userId, PasswordType passwordType) {
        UserAuth userAuth = findByUserIdAndPasswordType(userId, passwordType);
        if (ESObjectUtils.isNull(userAuth)) {
            throw new UserException(UserErrorEnum.USER_AUTH_NOT_FOUND);
        }
        super.deleteById(userAuth.getId());
    }

    public UserAuth findByUserIdAndPasswordType(String userId, PasswordType passwordType) {
        return userAuthDao.findByUserIdAndPasswordType(userId, passwordType);
    }
}
