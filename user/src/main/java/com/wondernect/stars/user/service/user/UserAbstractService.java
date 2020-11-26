package com.wondernect.stars.user.service.user;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.dto.*;
import com.wondernect.stars.user.dto.auth.local.SaveUserLocalAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.SaveUserThirdAuthRequestDTO;
import com.wondernect.stars.user.dto.auth.third.UserThirdAuthResponseDTO;
import com.wondernect.stars.user.em.AppType;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.User;
import com.wondernect.stars.user.service.localauth.UserLocalAuthService;
import com.wondernect.stars.user.service.thirdauth.UserThirdAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultUserAPI
 * Author: chenxun
 * Date: 2019/4/7 11:14
 * Description:
 */
public abstract class UserAbstractService extends BaseStringService<UserResponseDTO, User> implements UserInterface {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserLocalAuthService userLocalAuthService;

    @Autowired
    private UserThirdAuthService userThirdAuthService;

    @Transactional
    @Override
    public UserResponseDTO createLocalUser(SaveLocalUserRequestDTO saveLocalUserRequestDTO) {
        User user = userManager.findByUsername(saveLocalUserRequestDTO.getUsername());
        if (ESObjectUtils.isNotNull(user)) {
            throw new UserException(UserErrorEnum.USER_USERNAME_HAS_REGIST);
        }
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getMobile()) &&
                !ESRegexUtils.isMobile(saveLocalUserRequestDTO.getMobile())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
        }
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getEmail()) &&
                !ESRegexUtils.isEmail(saveLocalUserRequestDTO.getEmail())) {
            throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
        }
        user = new User(
                saveLocalUserRequestDTO.getUserType(),
                saveLocalUserRequestDTO.getUsername(),
                saveLocalUserRequestDTO.getName(),
                saveLocalUserRequestDTO.getGender(),
                saveLocalUserRequestDTO.getAvatar(),
                saveLocalUserRequestDTO.getMobile(),
                saveLocalUserRequestDTO.getEmail(),
                saveLocalUserRequestDTO.getLocation(),
                saveLocalUserRequestDTO.getRemark(),
                saveLocalUserRequestDTO.getRoleTypeId(),
                saveLocalUserRequestDTO.getRoleId(),
                saveLocalUserRequestDTO.getEnable(),
                saveLocalUserRequestDTO.getEditable(),
                saveLocalUserRequestDTO.getDeletable()
        );
        UserResponseDTO userResponseDTO = super.save(user);
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getPassword())) {
            userLocalAuthService.create(userResponseDTO.getId(), new SaveUserLocalAuthRequestDTO(saveLocalUserRequestDTO.getPassword()));
        }
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO createThirdUser(SaveThirdUserRequestDTO saveThirdUserRequestDTO) {
        UserThirdAuthResponseDTO userThirdAuthResponseDTO = userThirdAuthService.findByAppTypeAndAppUserId(saveThirdUserRequestDTO.getAppType(), saveThirdUserRequestDTO.getAppUserId());
        if (ESObjectUtils.isNotNull(userThirdAuthResponseDTO)) {
            throw new UserException(UserErrorEnum.USER_APP_HAS_REGIST);
        }
        if (ESStringUtils.isNotBlank(saveThirdUserRequestDTO.getMobile()) &&
                !ESRegexUtils.isMobile(saveThirdUserRequestDTO.getMobile())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
        }
        if (ESStringUtils.isNotBlank(saveThirdUserRequestDTO.getEmail()) &&
                !ESRegexUtils.isEmail(saveThirdUserRequestDTO.getEmail())) {
            throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
        }
        String name = saveThirdUserRequestDTO.getName();
        if (ESStringUtils.isBlank(name)) {
            name = saveThirdUserRequestDTO.getAppUserName();
        }
        String avatar = saveThirdUserRequestDTO.getAvatar();
        if (ESStringUtils.isBlank(avatar)) {
            avatar = saveThirdUserRequestDTO.getAppUserAvatar();
        }
        User user = new User(
                saveThirdUserRequestDTO.getUserType(),
                null,
                name,
                saveThirdUserRequestDTO.getGender(),
                avatar,
                saveThirdUserRequestDTO.getMobile(),
                saveThirdUserRequestDTO.getEmail(),
                saveThirdUserRequestDTO.getLocation(),
                saveThirdUserRequestDTO.getRemark(),
                saveThirdUserRequestDTO.getRoleTypeId(),
                saveThirdUserRequestDTO.getRoleId(),
                saveThirdUserRequestDTO.getEnable(),
                saveThirdUserRequestDTO.getEditable(),
                saveThirdUserRequestDTO.getDeletable()
        );
        if (ESStringUtils.isNotBlank(saveThirdUserRequestDTO.getId())) {
            user.setId(saveThirdUserRequestDTO.getId());
        }
        UserResponseDTO userResponseDTO = super.save(user);
        userThirdAuthService.create(userResponseDTO.getId(), new SaveUserThirdAuthRequestDTO(saveThirdUserRequestDTO.getAppType(), saveThirdUserRequestDTO.getAppUserId(), saveThirdUserRequestDTO.getAppUserName(), saveThirdUserRequestDTO.getAppUserAvatar()));
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO update(String userId, SaveLocalUserRequestDTO saveLocalUserRequestDTO) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getUsername()) &&
                !ESStringUtils.equalsIgnoreCase(saveLocalUserRequestDTO.getUsername(), user.getUsername())) {
            if (ESObjectUtils.isNotNull(userManager.findByUsername(saveLocalUserRequestDTO.getUsername()))) {
                throw new UserException(UserErrorEnum.USER_USERNAME_HAS_REGIST);
            }
        }
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getMobile()) &&
                !ESRegexUtils.isMobile(saveLocalUserRequestDTO.getMobile())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
        }
        if (ESStringUtils.isNotBlank(saveLocalUserRequestDTO.getEmail()) &&
                !ESRegexUtils.isEmail(saveLocalUserRequestDTO.getEmail())) {
            throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveLocalUserRequestDTO, user);
        return super.save(user);
    }

    @Transactional
    public void enable(String userId, Boolean enable) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (ESObjectUtils.isNotNull(user.getEditable()) && !user.getEditable()) {
            throw new BusinessException("用户不可编辑");
        }
        user.setEnable(enable);
        super.saveEntity(user);
    }

    @Override
    public void deleteById(String userId) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        super.deleteById(userId);
        switch (user.getUserType()) {
            case LOCAL:
            {
                userLocalAuthService.deleteById(userId);
                break;
            }
            case THIRD:
            {
                userThirdAuthService.deleteByUserId(userId);
                break;
            }
        }
    }

    @Override
    public UserResponseDTO findById(String userId) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        return generate(user);
    }

    @Override
    public UserResponseDTO findByMobile(String mobile) {
        User user = userManager.findByMobile(mobile);
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        return generate(user);
    }

    @Override
    public UserResponseDTO findByEmail(String email) {
        User user = userManager.findByEmail(email);
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        return generate(user);
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        User user = userManager.findByUsername(username);
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        return generate(user);
    }

    @Override
    public UserResponseDTO findByAppTypeAndAppUserId(AppType appType, String appUserId) {
        UserThirdAuthResponseDTO userThirdAuthResponseDTO = userThirdAuthService.findByAppTypeAndAppUserId(appType, appUserId);
        if (ESObjectUtils.isNull(userThirdAuthResponseDTO)) {
            return null;
        }
        User user = super.findEntityById(userThirdAuthResponseDTO.getUserId());
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        return generate(user);
    }

    @Override
    public List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("username", listUserRequestDTO.getUsername()));
        userCriteria.add(Restrictions.eq("roleTypeId", listUserRequestDTO.getRoleTypeId()));
        userCriteria.add(Restrictions.eq("roleId", listUserRequestDTO.getRoleId()));
        userCriteria.add(Restrictions.eq("enable", listUserRequestDTO.getEnable()));
        return super.findAll(userCriteria, listUserRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("username", pageUserRequestDTO.getUsername()));
        userCriteria.add(Restrictions.eq("roleTypeId", pageUserRequestDTO.getRoleTypeId()));
        userCriteria.add(Restrictions.eq("roleId", pageUserRequestDTO.getRoleId()));
        userCriteria.add(Restrictions.eq("enable", pageUserRequestDTO.getEnable()));
        return super.findAll(userCriteria, pageUserRequestDTO.getPageRequestData());
    }

    public UserResponseDTO generate(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        ESBeanUtils.copyProperties(user, userResponseDTO);
        userResponseDTO.setUserType(user.getUserType().name());
        userResponseDTO.setGender(user.getGender().name());
        return userResponseDTO;
    }
}
