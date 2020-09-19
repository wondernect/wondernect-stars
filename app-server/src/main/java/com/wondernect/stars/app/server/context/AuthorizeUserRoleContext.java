package com.wondernect.stars.app.server.context;

import com.wondernect.elements.authorize.context.impl.AbstractWondernectAuthorizeContext;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:王威
 * @Date: 2020/7/13 17:12
 * @Version 1.0
 */
@Component
public class AuthorizeUserRoleContext extends AbstractWondernectAuthorizeContext {

    @Autowired
    private AppService appService;

    @Override
    public boolean authorizeAppSecret(String appId, String encryptSecret) {
        appService.auth(appId, new AuthAppRequestDTO(encryptSecret));
        return true;
    }
}
