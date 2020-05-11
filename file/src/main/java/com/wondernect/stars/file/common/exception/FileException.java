package com.wondernect.stars.file.common.exception;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.stars.file.common.error.FileErrorEnum;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: FileException
 * Author: chenxun
 * Date: 2018/12/29 15:41
 * Description:
 */
public class FileException extends BusinessException {

    private static final long serialVersionUID = -1244168675265326165L;

    public FileException(FileErrorEnum fileErrorEnum) {
        super(fileErrorEnum.getMessage(), fileErrorEnum.getCode());
    }

    public FileException(FileErrorEnum fileErrorEnum, String customMessage) {
        super(customMessage, fileErrorEnum.getCode());
    }
}
