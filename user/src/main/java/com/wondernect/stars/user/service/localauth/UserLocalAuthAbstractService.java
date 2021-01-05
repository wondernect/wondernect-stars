package com.wondernect.stars.user.service.localauth;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseService;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.dto.auth.local.AuthUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.local.UserLocalAuthResponseDTO;
import com.wondernect.stars.user.model.UserLocalAuth;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AbstractUserLocalService
 * Author: chenxun
 * Date: 2020-06-25 18:06
 * Description:
 */
public abstract class UserLocalAuthAbstractService extends BaseService<UserLocalAuthResponseDTO, UserLocalAuth, String> implements UserLocalAuthInterface {

    @Override
    public UserLocalAuthResponseDTO create(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO) {
        UserLocalAuth userLocalAuth = super.findEntityById(userId);
        if (ESObjectUtils.isNotNull(userLocalAuth)) {
            throw new UserException(UserErrorEnum.USER_LOCAL_AUTH_HAS_REGIST);
        }
        return super.save(
                new UserLocalAuth(
                        userId,
                        encryptUserLocalAuthPassword(saveUserLocalAuthRequestDTO.getPassword())
                )
        );
    }

    @Override
    public UserLocalAuthResponseDTO update(String userId, SaveUserLocalAuthRequestDTO saveUserLocalAuthRequestDTO) {
        UserLocalAuth userLocalAuth = super.findEntityById(userId);
        if (ESObjectUtils.isNull(userLocalAuth)) {
            throw new UserException(UserErrorEnum.USER_LOCAL_AUTH_NOT_FOUND);
        }
        userLocalAuth.setPassword(encryptUserLocalAuthPassword(saveUserLocalAuthRequestDTO.getPassword()));
        return super.save(userLocalAuth);
    }

    public void auth(AuthUserLocalAuthRequestDTO authUserLocalAuthRequestDTO) {
        UserLocalAuth userLocalAuth = super.findEntityById(authUserLocalAuthRequestDTO.getUserId());
        if (ESObjectUtils.isNull(userLocalAuth)) {
            throw new UserException(UserErrorEnum.USER_LOCAL_AUTH_NOT_FOUND);
        }
        if (!userLocalAuth.getPassword().equals(authUserLocalAuthRequestDTO.getPassword())) {
            throw new UserException(UserErrorEnum.USER_LOCAL_AUTH_PASSWORD_FAILED);
        }
    }

    public abstract String encryptUserLocalAuthPassword(String password);

    @Override
    public UserLocalAuthResponseDTO generate(UserLocalAuth userLocalAuth) {
        UserLocalAuthResponseDTO userLocalAuthResponseDTO = new UserLocalAuthResponseDTO();
        ESBeanUtils.copyProperties(userLocalAuth, userLocalAuthResponseDTO);
        return userLocalAuthResponseDTO;
    }
}
