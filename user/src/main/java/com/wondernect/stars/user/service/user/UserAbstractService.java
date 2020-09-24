package com.wondernect.stars.user.service.user;

import com.wondernect.elements.authorize.context.WondernectCommonContext;
import com.wondernect.elements.common.error.BusinessError;
import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.criteria.em.LogicalOperator;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.user.common.error.UserErrorEnum;
import com.wondernect.stars.user.common.exception.UserException;
import com.wondernect.stars.user.dto.ListUserRequestDTO;
import com.wondernect.stars.user.dto.PageUserRequestDTO;
import com.wondernect.stars.user.dto.SaveUserRequestDTO;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.manager.UserManager;
import com.wondernect.stars.user.model.User;
import org.hibernate.criterion.MatchMode;
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
    private WondernectCommonContext wondernectCommonContext;

    @Autowired
    private UserManager userManager;

    @Override
    public UserResponseDTO create(SaveUserRequestDTO saveUserRequestDTO) {
        User user = userManager.findByUsername(saveUserRequestDTO.getUsername(), wondernectCommonContext.getAuthorizeData().getAppId());
        if (ESObjectUtils.isNotNull(user)) {
            throw new UserException(UserErrorEnum.USER_USERNAME_HAS_REGIST);
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getMobile()) &&
                !ESRegexUtils.isMobile(saveUserRequestDTO.getMobile())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getEmail()) &&
                !ESRegexUtils.isEmail(saveUserRequestDTO.getEmail())) {
            throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
        }
        user = new User(
                saveUserRequestDTO.getUserType(),
                saveUserRequestDTO.getUsername(),
                saveUserRequestDTO.getName(),
                saveUserRequestDTO.getGender(),
                saveUserRequestDTO.getAvatar(),
                saveUserRequestDTO.getMobile(),
                saveUserRequestDTO.getEmail(),
                saveUserRequestDTO.getLocation(),
                saveUserRequestDTO.getRemark(),
                saveUserRequestDTO.getRoleTypeId(),
                saveUserRequestDTO.getRoleId(),
                saveUserRequestDTO.getEnable(),
                saveUserRequestDTO.getEditable(),
                saveUserRequestDTO.getDeletable()
        );
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getId())) {
            user.setId(saveUserRequestDTO.getId());
        }
        return super.save(user);
    }

    @Override
    public UserResponseDTO update(String userId, SaveUserRequestDTO saveUserRequestDTO) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (!ESStringUtils.equals(user.getCreateApp(), wondernectCommonContext.getAuthorizeData().getAppId())) {
            throw new BusinessException(BusinessError.INVALID_APP_USER_DATA);
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getUsername()) &&
                !ESStringUtils.equalsIgnoreCase(saveUserRequestDTO.getUsername(), user.getUsername())) {
            if (ESObjectUtils.isNotNull(userManager.findByUsername(saveUserRequestDTO.getUsername(), wondernectCommonContext.getAuthorizeData().getAppId()))) {
                throw new UserException(UserErrorEnum.USER_USERNAME_HAS_REGIST);
            }
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getMobile()) &&
                !ESRegexUtils.isMobile(saveUserRequestDTO.getMobile())) {
            throw new UserException(UserErrorEnum.USER_MOBILE_INVALID);
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getEmail()) &&
                !ESRegexUtils.isEmail(saveUserRequestDTO.getEmail())) {
            throw new UserException(UserErrorEnum.USER_EMAIL_INVALID);
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveUserRequestDTO, user);
        return super.save(user);
    }

    @Transactional
    public void enable(String userId, Boolean enable) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (!ESStringUtils.equals(user.getCreateApp(), wondernectCommonContext.getAuthorizeData().getAppId())) {
            throw new BusinessException(BusinessError.INVALID_APP_USER_DATA);
        }
        if (ESObjectUtils.isNotNull(user.getEditable()) && !user.getEditable()) {
            throw new BusinessException("用户不可编辑");
        }
        user.setEnable(enable);
        super.saveEntity(user);
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        User user = userManager.findByUsername(username, wondernectCommonContext.getAuthorizeData().getAppId());
        if (ESObjectUtils.isNull(user)) {
            return null;
        }
        if (!ESStringUtils.equals(user.getCreateApp(), wondernectCommonContext.getAuthorizeData().getAppId())) {
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
        userCriteria.add(Restrictions.eq("createApp", wondernectCommonContext.getAuthorizeData().getAppId()));
        return super.findAll(userCriteria, listUserRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO) {
        Criteria<User> userCriteria = new Criteria<>();
        userCriteria.add(Restrictions.eq("username", pageUserRequestDTO.getUsername()));
        userCriteria.add(Restrictions.eq("roleTypeId", pageUserRequestDTO.getRoleTypeId()));
        userCriteria.add(Restrictions.eq("roleId", pageUserRequestDTO.getRoleId()));
        userCriteria.add(Restrictions.eq("enable", pageUserRequestDTO.getEnable()));
        userCriteria.add(Restrictions.eq("createApp", wondernectCommonContext.getAuthorizeData().getAppId()));
        return super.findAll(userCriteria, pageUserRequestDTO.getPageRequestData());
    }

    public UserResponseDTO generate(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        ESBeanUtils.copyProperties(user, userResponseDTO);
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUserType(user.getUserType().name());
        userResponseDTO.setGender(user.getGender().name());
        return userResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        return null;
    }
}
