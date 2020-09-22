package com.wondernect.stars.user.server;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.boot.application.event.WondernectBootEvent;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.user.em.Gender;
import com.wondernect.stars.user.em.UserType;
import com.wondernect.stars.user.manager.UserLocalAuthManager;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.model.UserLocalAuth;
import com.wondernect.stars.user.server.config.UserConfigProperties;
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
    private UserConfigProperties userConfigProperties;

    @Autowired
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserLocalAuthManager userAuthManager;

    @Override
    public void onApplicationEvent(WondernectBootEvent wondernectBootEvent) {
        switch (wondernectBootEvent.getWondernectBootEventType()) {
            case BOOT:
            {
                wondernectCommonContext.getAuthorizeData().setAppId(userConfigProperties.getAppId());
                wondernectCommonContext.getAuthorizeData().setUserId(userConfigProperties.getUserId());
                // 初始化UMS应用管理员
                if (ESObjectUtils.isNull(userManager.findById(userConfigProperties.getUserId()))) {
                    User user = new User(
                            UserType.LOCAL,
                            userConfigProperties.getUsername(),
                            "UMS管理员",
                            Gender.UNKNOWN,
                            null,
                            "",
                            "",
                            null,
                            null,
                            userConfigProperties.getRoleTypeId(),
                            userConfigProperties.getRoleId(),
                            true,
                            true,
                            false
                    );
                    user.setId(userConfigProperties.getUserId());
                    userManager.save(user);
                    userAuthManager.save(
                            new UserLocalAuth(
                                    userConfigProperties.getUserId(),
                                    userConfigProperties.getPassword()
                            )
                    );
                }
                break;
            }
            default:
                break;
        }
    }
}
