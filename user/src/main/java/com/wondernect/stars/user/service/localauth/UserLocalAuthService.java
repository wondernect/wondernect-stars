package com.wondernect.stars.user.service.localauth;

import com.wondernect.elements.common.utils.ESSecurityUtils;
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

    @Override
    public String encryptUserLocalAuthPassword(String password) {
        return ESSecurityUtils.doubleMd5Crypt(password);
    }
}
