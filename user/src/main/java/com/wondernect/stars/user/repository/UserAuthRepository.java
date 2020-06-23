package com.wondernect.stars.user.repository;

import com.wondernect.elements.rdb.base.repository.BaseStringRepository;
import com.wondernect.stars.user.model.UserAuth;
import com.wondernect.stars.user.model.em.PasswordType;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: UserAuthRepository
 * Author: chenxun
 * Date: 2018/10/24 15:38
 * Description:
 */
public interface UserAuthRepository extends BaseStringRepository<UserAuth> {

    UserAuth findByUserIdAndPasswordType(String userId, PasswordType passwordType);
}
