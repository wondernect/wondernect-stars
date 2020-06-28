package com.wondernect.stars.user.service.localauth;

import com.wondernect.elements.common.utils.ESSecurityUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.stars.user.config.UserConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserLocalAuthService
 * Author: chenxun
 * Date: 2020-06-26 12:11
 * Description:
 */
@Service
public class UserLocalAuthService extends UserLocalAuthAbstractService {

    @Autowired
    private UserConfigProperties userConfigProperties;

    @Override
    public String encryptUserLocalAuthPassword(String password) {
        if (ESStringUtils.isNotBlank(userConfigProperties.getPasswordEncryptSalt())) {
            password = password + "|" + userConfigProperties.getPasswordEncryptSalt();
        }
        return ESSecurityUtils.doubleMd5Crypt(password);
    }
}
