package com.wondernect.stars.user.service.astract;

import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESRegexUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
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
import com.wondernect.stars.user.service.InitUserService;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: DefaultUserAPI
 * Author: chenxun
 * Date: 2019/4/7 11:14
 * Description:
 */
public abstract class AbstractUserService extends BaseStringService<UserResponseDTO, User> implements InitUserService {

    @Autowired
    private UserManager userManager;

    @Override
    public UserResponseDTO create(SaveUserRequestDTO saveUserRequestDTO) {
        User user = userManager.findByUsername(saveUserRequestDTO.getUsername());
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
        return super.save(
                new User(
                        saveUserRequestDTO.getUserType(),
                        saveUserRequestDTO.getUsername(),
                        saveUserRequestDTO.getName(),
                        saveUserRequestDTO.getGender(),
                        saveUserRequestDTO.getAvatar(),
                        saveUserRequestDTO.getMobile(),
                        saveUserRequestDTO.getEmail(),
                        saveUserRequestDTO.getLocation(),
                        saveUserRequestDTO.getRemark(),
                        saveUserRequestDTO.getRoleType(),
                        saveUserRequestDTO.getRole(),
                        saveUserRequestDTO.getEnable(),
                        saveUserRequestDTO.getEditable(),
                        saveUserRequestDTO.getDeletable()
                )
        );
    }

    @Override
    public UserResponseDTO update(String userId, SaveUserRequestDTO saveUserRequestDTO) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new UserException(UserErrorEnum.USER_NOT_FOUND);
        }
        if (ESStringUtils.isNotBlank(saveUserRequestDTO.getUsername()) &&
                !ESStringUtils.equalsIgnoreCase(saveUserRequestDTO.getUsername(), user.getUsername())) {
            if (ESObjectUtils.isNotNull(userManager.findByUsername(saveUserRequestDTO.getUsername()))) {
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
        ESBeanUtils.copyProperties(saveUserRequestDTO, user);
        return super.save(user);
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
    public List<UserResponseDTO> list(ListUserRequestDTO listUserRequestDTO) {
        Criteria<User> userCriteria = new Criteria<>(LogicalOperator.OR);
        userCriteria.add(Restrictions.like("username", listUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        userCriteria.add(Restrictions.like("mobile", listUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        userCriteria.add(Restrictions.like("email", listUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(userCriteria, listUserRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<UserResponseDTO> page(PageUserRequestDTO pageUserRequestDTO) {
        Criteria<User> userCriteria = new Criteria<>(LogicalOperator.OR);
        userCriteria.add(Restrictions.like("username", pageUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        userCriteria.add(Restrictions.like("mobile", pageUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        userCriteria.add(Restrictions.like("email", pageUserRequestDTO.getValue(), MatchMode.ANYWHERE));
        return super.findAll(userCriteria, pageUserRequestDTO.getPageRequestData());
    }

    public UserResponseDTO generate(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        ESBeanUtils.copyProperties(user, userResponseDTO);
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setUserType(user.getUserType().name());
        userResponseDTO.setGender(user.getGender().name());
        return userResponseDTO;
    }
}
