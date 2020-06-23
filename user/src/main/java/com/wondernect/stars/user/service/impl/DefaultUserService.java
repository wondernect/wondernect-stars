package com.wondernect.stars.user.service.impl;

import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.manager.OpenUserManager;
import com.wondernect.stars.user.manager.UserAuthManager;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.OpenUser;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.model.UserAuth;
import com.wondernect.stars.user.model.em.PasswordType;
import com.wondernect.stars.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultUserAPI
 * Author: chenxun
 * Date: 2019/4/7 11:14
 * Description:
 */
@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserManager userManager;

    @Autowired
    private OpenUserManager openUserManager;

    @Autowired
    private UserAuthManager userAuthManager;

    @Override
    public User userRegist(User user, OpenUser openUser, UserAuth userAuth) {
        switch (user.getUserRegistType()) {
            case MOBILE:
            {
                if (ESObjectUtils.isNotNull(userManager.findByMobile(user.getMobile()))) {
                    throw new UserException(UserErrorEnum.USER_MOBILE_HAS_REGIST);
                }
                break;
            }
            case EMAIL:
            {
                if (ESObjectUtils.isNotNull(userManager.findByEmail(user.getEmail()))) {
                    throw new UserException(UserErrorEnum.USER_EMAIL_HAS_REGIST);
                }
                break;
            }
            case APP:
            {
                if (ESObjectUtils.isNotNull(openUserManager.findByAppTypeAndAppUserId(openUser.getAppType(), openUser.getAppUserId()))) {
                    throw new UserException(UserErrorEnum.USER_APP_HAS_REGIST);
                }
                break;
            }
            default:
            {
                throw new UserException(UserErrorEnum.USER_REGIST_TYPE_INVALID);
            }
        }
        User userSave = userManager.save(user);
        if (ESObjectUtils.isNotNull(openUser)) {
            openUser.setUserId(userSave.getId());
            openUserManager.save(openUser);
        }
        if (ESObjectUtils.isNotNull(userAuth) && ESStringUtils.isNotBlank(userAuth.getPassword())) {
            userAuth.setUserId(userSave.getId());
            userAuthManager.save(userAuth);
        }
        return userSave;
    }

    @Override
    public User updateUser(User userSync) {
        User user = userManager.findById(userSync.getId());
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (ESStringUtils.isAllBlank(userSync.getMobile(), userSync.getEmail())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_EMAIL_CAN_NOT_ALL_NULL);
        }
        if (ESStringUtils.isNotBlank(userSync.getMobile()) && ESStringUtils.isNotBlank(userSync.getEmail())) {
            if (!ESRegexUtils.isMobile(userSync.getMobile()) || !ESRegexUtils.isEmail(userSync.getEmail())) {
                throw new UserException(UserErrorEnum.USER_MOBILE_EMAIL_INVALID);
            }
            if (!ESStringUtils.equalsIgnoreCase(userSync.getMobile(), user.getMobile()) && ESObjectUtils.isNotNull(userManager.findByMobile(userSync.getMobile()))) {
                throw new UserException(UserErrorEnum.USER_MOBILE_HAS_REGIST);
            }
            if (!ESStringUtils.equalsIgnoreCase(userSync.getEmail(), user.getEmail()) && ESObjectUtils.isNotNull(userManager.findByEmail(userSync.getEmail()))) {
                throw new UserException(UserErrorEnum.USER_EMAIL_HAS_REGIST);
            }
            user.setMobile(userSync.getMobile());
            user.setEmail(userSync.getEmail());
        } else {
            if (ESStringUtils.isNotBlank(userSync.getMobile())) {
                if (!ESRegexUtils.isMobile(userSync.getMobile())) {
                    throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
                }
                if (!ESStringUtils.equalsIgnoreCase(userSync.getMobile(), user.getMobile()) && ESObjectUtils.isNotNull(userManager.findByMobile(userSync.getMobile()))) {
                    throw new UserException(UserErrorEnum.USER_MOBILE_HAS_REGIST);
                }
                user.setMobile(userSync.getMobile());
            } else {
                if (!ESRegexUtils.isEmail(userSync.getEmail())) {
                    throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
                }
                if (!ESStringUtils.equalsIgnoreCase(userSync.getEmail(), user.getEmail()) && ESObjectUtils.isNotNull(userManager.findByEmail(userSync.getEmail()))) {
                    throw new UserException(UserErrorEnum.USER_EMAIL_HAS_REGIST);
                }
                user.setEmail(userSync.getEmail());
            }
        }
        if (ESObjectUtils.isNotNull(userSync.getGender())) {
            user.setGender(userSync.getGender());
        }
        if (ESStringUtils.isNotBlank(userSync.getName())) {
            user.setName(userSync.getName());
        }
        if (ESStringUtils.isNotBlank(userSync.getAvatar())) {
            user.setAvatar(userSync.getAvatar());
        }
        if (ESStringUtils.isNotBlank(userSync.getLocation())) {
            user.setLocation(userSync.getLocation());
        }
        return userManager.save(user);
    }

    @Override
    public void deleteByUserId(String operator, String userId) {
        if (ESStringUtils.equalsIgnoreCase(userId, operator)) {
            throw new UserException(UserErrorEnum.USER_DELETE_OWN_ALLOWED);
        }
        User user = userManager.findById(userId);
        if (ESObjectUtils.isNotNull(user)) {
            if (!ESStringUtils.equalsIgnoreCase(user.getCreateUser(), operator)) {
                throw new UserException(UserErrorEnum.USER_DELETE_NOT_ALLOWED);
            }
            userManager.deleteById(userId);
        }
    }

    @Override
    public User findByUserId(String userId) {
        return userManager.findById(userId);
    }

    @Override
    public User findByUsername(String username) {
        User user;
        if (ESRegexUtils.isMobile(username)) {
            user = userManager.findByMobile(username);
        } else if (ESRegexUtils.isEmail(username)) {
            user = userManager.findByEmail(username);
        } else {
            throw new UserException(UserErrorEnum.USER_MOBILE_EMAIL_INVALID);
        }
        return user;
    }

    @Override
    public void authUserPassword(UserAuth userAuth) {
        if (ESObjectUtils.isNull(userAuth.getPasswordType())) {
            userAuth.setPasswordType(PasswordType.USERNAME_PASSWORD);
        }
        User user = userManager.findById(userAuth.getUserId());
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        UserAuth userAuthGet = userAuthManager.findByUserIdAndPasswordType(userAuth.getUserId(), userAuth.getPasswordType());
        if (ESObjectUtils.isNull(userAuthGet)) {
            throw new UserException(UserErrorEnum.USER_AUTH_NOT_FOUND);
        }
        if (!ESStringUtils.equalsIgnoreCase(userAuth.getPassword(), userAuthGet.getPassword())) {
            throw new UserException(UserErrorEnum.USER_AUTH_FAILED);
        }
    }

    @Override
    public void setUserPassword(UserAuth userAuth) {
        if (ESObjectUtils.isNull(userAuth.getPasswordType())) {
            userAuth.setPasswordType(PasswordType.USERNAME_PASSWORD);
        }
        UserAuth userAuthGet = userAuthManager.findByUserIdAndPasswordType(userAuth.getUserId(), userAuth.getPasswordType());
        if (ESObjectUtils.isNull(userAuthGet)) {
            userAuthGet = new UserAuth(userAuth.getUserId(), userAuth.getPasswordType(), userAuth.getPassword());
        } else {
            userAuthGet.setPassword(userAuth.getPassword());
        }
        userAuthManager.save(userAuthGet);
    }

    @Override
    public List<User> findList(String createUser, String name, String mobile, String email, List<SortData> sortDataList) {
        return userManager.findAll(createUser, name, mobile, email, sortDataList);
    }

    @Override
    public PageResponseData<User> findPage(String createUser, String name, String mobile, String email, PageRequestData pageRequestData) {
        return userManager.findAll(createUser, name, mobile, email, pageRequestData);
    }
}
