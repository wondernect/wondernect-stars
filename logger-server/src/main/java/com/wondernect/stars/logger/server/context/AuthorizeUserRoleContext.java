package com.wondernect.stars.logger.server.context;

import com.wondernect.elements.authorize.context.impl.AbstractWondernectAuthorizeContext;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.feign.app.AppServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeUserRoleContext extends AbstractWondernectAuthorizeContext {

    @Autowired
    private AppServerService appServerService;

    @Override
    public boolean authorizeAppSecret(String appId, String encryptSecret) {
        return appServerService.auth(appId, new AuthAppRequestDTO(encryptSecret));
    }
}
