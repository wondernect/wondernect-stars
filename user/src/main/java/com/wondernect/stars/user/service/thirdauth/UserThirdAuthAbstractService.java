package com.wondernect.stars.user.service.thirdauth;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.dto.auth.third.AuthUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.manager.UserThirdAuthManager;
import com.wondernect.stars.user.model.UserThirdAuth;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: AbstractUserLocalService
 * Author: chenxun
 * Date: 2020-06-25 18:06
 * Description:
 */
public abstract class UserThirdAuthAbstractService extends BaseStringService<UserThirdAuthResponseDTO, UserThirdAuth> implements UserThirdAuthInterface {

    @Autowired
    private UserThirdAuthManager userThirdAuthManager;

    @Override
    @Transactional
    public UserThirdAuthResponseDTO create(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByUserIdAndAppType(userId, saveUserThirdAuthRequestDTO.getAppType());
        if (ESObjectUtils.isNotNull(userThirdAuth)) {
            throw new UserException(UserErrorEnum.USER_THIRD_AUTH_HAS_REGIST);
        }
        return super.save(
                new UserThirdAuth(
                        userId,
                        saveUserThirdAuthRequestDTO.getAppType(),
                        saveUserThirdAuthRequestDTO.getAppUserId(),
                        saveUserThirdAuthRequestDTO.getAppUserName(),
                        saveUserThirdAuthRequestDTO.getAppUserAvatar()
                )
        );
    }

    @Override
    @Transactional
    public UserThirdAuthResponseDTO update(String userId, SaveUserThirdAuthRequestDTO saveUserThirdAuthRequestDTO) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByUserIdAndAppType(userId, saveUserThirdAuthRequestDTO.getAppType());
        if (ESObjectUtils.isNull(userThirdAuth)) {
            throw new UserException(UserErrorEnum.USER_THIRD_AUTH_NOT_FOUND);
        }
        userThirdAuth.setAppUserId(saveUserThirdAuthRequestDTO.getAppUserId());
        userThirdAuth.setAppUserName(saveUserThirdAuthRequestDTO.getAppUserName());
        userThirdAuth.setAppUserAvatar(saveUserThirdAuthRequestDTO.getAppUserAvatar());
        return super.save(userThirdAuth);
    }

    @Override
    @Transactional
    public void deleteByUserIdAndAppType(String userId, AppType appType) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByUserIdAndAppType(userId, appType);
        if (ESObjectUtils.isNull(userThirdAuth)) {
            throw new UserException(UserErrorEnum.USER_THIRD_AUTH_NOT_FOUND);
        }
        super.deleteById(userThirdAuth.getId());
    }

    @Override
    public void deleteByUserId(String userId) {
        Criteria<UserThirdAuth> userThirdAuthCriteria = new Criteria<>();
        userThirdAuthCriteria.add(Restrictions.eq("userId", userId));
        List<UserThirdAuth> userThirdAuthList = super.findAllEntity(userThirdAuthCriteria, new ArrayList<>());
        if (CollectionUtils.isNotEmpty(userThirdAuthList)) {
            for (UserThirdAuth userThirdAuth : userThirdAuthList) {
                super.deleteById(userThirdAuth.getId());
            }
        }
    }

    @Override
    public UserThirdAuthResponseDTO findByUserIdAndAppType(String userId, AppType appType) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByUserIdAndAppType(userId, appType);
        if (ESObjectUtils.isNull(userThirdAuth)) {
            return null;
        }
        return generate(userThirdAuth);
    }

    @Override
    public UserThirdAuthResponseDTO findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByAppTypeAndAppUserId(appType, appUserId);
        if (ESObjectUtils.isNull(userThirdAuth)) {
            return null;
        }
        return generate(userThirdAuth);
    }

    public void auth(AuthUserThirdAuthRequestDTO authUserThirdAuthRequestDTO) {
        UserThirdAuth userThirdAuth = userThirdAuthManager.findByAppTypeAndAppUserId(authUserThirdAuthRequestDTO.getAppType(), authUserThirdAuthRequestDTO.getAppUserId());
        if (ESObjectUtils.isNull(userThirdAuth)) {
            throw new UserException(UserErrorEnum.USER_THIRD_AUTH_NOT_FOUND);
        }
    }

    @Override
    public UserThirdAuthResponseDTO generate(UserThirdAuth userThirdAuth) {
        UserThirdAuthResponseDTO userThirdAuthResponseDTO = new UserThirdAuthResponseDTO();
        ESBeanUtils.copyProperties(userThirdAuth, userThirdAuthResponseDTO);
        return userThirdAuthResponseDTO;
    }
}
