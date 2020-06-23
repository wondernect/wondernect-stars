package com.wondernect.stars.user.common.exception;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.stars.user.common.error.UserErrorEnum;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: UserException
 * Author: chenxun
 * Date: 2018/12/29 15:41
 * Description:
 */
public class UserException extends BusinessException {

    private static final long serialVersionUID = -1244168675265326165L;

    public UserException(UserErrorEnum userErrorEnum) {
        super(userErrorEnum.getMessage(), userErrorEnum.getCode());
    }

    public UserException(UserErrorEnum userErrorEnum, String customMessage) {
        super(customMessage, userErrorEnum.getCode());
    }
}
