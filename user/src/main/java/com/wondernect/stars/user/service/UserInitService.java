package com.wondernect.stars.user.service;

import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.stars.user.config.UserConfigProperties;
import com.wondernect.stars.user.em.Gender;
import com.wondernect.stars.user.em.UserType;
import com.wondernect.stars.user.manager.UserLocalAuthManager;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.UserLocalAuth;
import com.wondernect.stars.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultUserOpenAPI
 * Author: chenxun
 * Date: 2019/4/8 11:32
 * Description:
 */
@Service
public class UserInitService implements ApplicationListener<WondernectBootEvent> {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserLocalAuthManager userAuthManager;

    @Autowired
    private UserConfigProperties userConfigProperties;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                if (ESStringUtils.isNotBlank(userConfigProperties.getUsername())) {
                    User user = userManager.findByUsername(userConfigProperties.getUsername());
                    if (ESObjectUtils.isNull(user)) {
                        user = userManager.save(
                                new User(
                                        UserType.LOCAL,
                                        userConfigProperties.getUsername(),
                                        userConfigProperties.getName(),
                                        Gender.UNKNOWN,
                                        null,
                                        userConfigProperties.getMobile(),
                                        userConfigProperties.getEmail(),
                                        null,
                                        null,
                                        userConfigProperties.getRoleType(),
                                        userConfigProperties.getRole(),
                                        true,
                                        true,
                                        false
                                )
                        );
                        userAuthManager.save(
                                new UserLocalAuth(
                                        user.getId(),
                                        userConfigProperties.getPassword()
                                )
                        );
                    }
                }
                break;
            }
            default:
                break;
        }
    }
}
