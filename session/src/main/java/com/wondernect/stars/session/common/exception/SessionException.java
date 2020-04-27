package com.wondernect.stars.session.common.exception;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.stars.session.common.error.SessionErrorEnum;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SessionException
 * Author: chenxun
 * Date: 2018/12/29 15:41
 * Description:
 */
public class SessionException extends BusinessException {

    private static final long serialVersionUID = -1244168675265326165L;

    public SessionException(SessionErrorEnum sessionErrorEnum) {
        super(sessionErrorEnum.getMessage(), sessionErrorEnum.getCode());
    }

    public SessionException(SessionErrorEnum sessionErrorEnum, String customMessage) {
        super(customMessage, sessionErrorEnum.getCode());
    }
}
