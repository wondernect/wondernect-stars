package com.wondernect.stars.user.service.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.stars.user.config.UserConfigProperties;
import com.wondernect.stars.user.manager.UserAuthManager;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.model.UserAuth;
import com.wondernect.stars.user.model.em.Gender;
import com.wondernect.stars.user.model.em.PasswordType;
import com.wondernect.stars.user.model.em.UserRegistType;
import com.wondernect.stars.user.service.UserOpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultUserOpenAPI
 * Author: chenxun
 * Date: 2019/4/8 11:32
 * Description:
 */
@Service
public class DefaultUserOpenService implements UserOpenService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserAuthManager userAuthManager;

    @Autowired
    private UserConfigProperties userConfigProperties;

    @Override
    public User initPlatformAdmin() {
        if (ESStringUtils.isNotBlank(userConfigProperties.getMobile())) {
            if (ESStringUtils.isNotBlank(userConfigProperties.getEmail())) {
                User mobileUser = userManager.findByMobile(userConfigProperties.getMobile());
                User emailUser = userManager.findByEmail(userConfigProperties.getEmail());
                if (ESObjectUtils.isNull(mobileUser) && ESObjectUtils.isNull(emailUser)) {
                    User user = userManager.save(new User(UserRegistType.EMAIL, Gender.UNKNOWN, userConfigProperties.getName(), null, userConfigProperties.getMobile(), userConfigProperties.getEmail(), null));
                    userAuthManager.save(new UserAuth(user.getId(), PasswordType.USERNAME_PASSWORD, userConfigProperties.getPassword()));
                    return user;
                }
            } else {
                User mobileUser = userManager.findByMobile(userConfigProperties.getMobile());
                if (ESObjectUtils.isNull(mobileUser)) {
                    User user = userManager.save(new User(UserRegistType.MOBILE, Gender.UNKNOWN, userConfigProperties.getName(), null, userConfigProperties.getMobile(), null, null));
                    userAuthManager.save(new UserAuth(user.getId(), PasswordType.USERNAME_PASSWORD, userConfigProperties.getPassword()));
                    return user;
                }
            }
        } else {
            if (ESStringUtils.isNotBlank(userConfigProperties.getEmail())) {
                User emailUser = userManager.findByEmail(userConfigProperties.getEmail());
                if (ESObjectUtils.isNull(emailUser)) {
                    User user = userManager.save(new User(UserRegistType.EMAIL, Gender.UNKNOWN, userConfigProperties.getName(), null, null, userConfigProperties.getEmail(), null));
                    userAuthManager.save(new UserAuth(user.getId(), PasswordType.USERNAME_PASSWORD, userConfigProperties.getPassword()));
                    return user;
                }
            }
        }
        return null;
    }
}
