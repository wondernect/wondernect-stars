package com.wondernect.stars.sms.server.context;

import com.wondernect.elements.authorize.context.impl.AbstractWondernectAuthorizeContext;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.feign.config.WondernectFeignHeaderConfigProperties;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.feign.app.AppServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorizeUserRoleContext extends AbstractWondernectAuthorizeContext {

    @Autowired
    private AppServerService appServerService;

    @Autowired
    private WondernectFeignHeaderConfigProperties wondernectFeignHeaderConfigProperties;

    @Override
    public boolean authorizeAppSecret(String appId, String encryptSecret) {
        return appServerService.auth(appId, new AuthAppRequestDTO(encryptSecret));
    }

    @Override
    public boolean authorizeStandAloneAppSecret(String appId, String encryptSecret) {
        if (ESStringUtils.isBlank(appId) || ESStringUtils.isBlank(encryptSecret)) {
            return false;
        }
        if (!ESStringUtils.equals(wondernectFeignHeaderConfigProperties.getAppId(), appId)) {
            return false;
        }
        if (!ESStringUtils.equals(wondernectFeignHeaderConfigProperties.getAppSecret(), encryptSecret)) {
            return false;
        }
        return true;
    }
}
