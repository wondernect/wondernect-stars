package com.wondernect.stars.user.service.user;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.stars.user.dto.UserResponseDTO;
import com.wondernect.stars.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: UserService
 * Author: chenxun
 * Date: 2020-06-25 19:33
 * Description:
 */
@Service
public class UserService extends UserAbstractService {

    /**
     * 激活/禁用
     */
    @Transactional
    public void enable(String userId, Boolean enable) {
        User user = super.findEntityById(userId);
        if (ESObjectUtils.isNull(user)) {
            throw new BusinessException("用户不存在");
        }
        if (ESObjectUtils.isNotNull(user.getEditable()) && !user.getEditable()) {
            throw new BusinessException("用户不可编辑");
        }
        user.setEnable(enable);
        super.saveEntity(user);
    }
}
