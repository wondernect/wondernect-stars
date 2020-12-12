package com.wondernect.stars.user.server.log;

import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.logger.request.AbstractRequestLoggerRecordService;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2020, wondernect.com
 * FileName: UserServerRequestLogService
 * Author: chenxun
 * Date: 2020/12/11 16:10
 * Description: 用户操作日志记录
 */
@Service
public class UserServerRequestLogService extends AbstractRequestLoggerRecordService {

    private static final String USER_SERVER_DEFAULT_LOG_LEVEL = "INFO";

    private static final String USER_SERVER_SERVICE = "wondernect-stars-user";

    @Override
    public void recordRequestLog(String level, String service, String module, String userId, String appId, String operation, String description, String requestId, String url, String method, String argValue, String returnValue, Long runStartTime, Long runTime, String ip, String devicePlatform, String deviceDescription) {
        if (ESStringUtils.isBlank(level)) {
            level = USER_SERVER_DEFAULT_LOG_LEVEL;
        }
        if (ESStringUtils.isBlank(service)) {
            service = USER_SERVER_SERVICE;
        }
        super.recordRequestLog(level, service, module, userId, appId, operation, description, requestId, url, method, argValue, returnValue, runStartTime, runTime, ip, devicePlatform, deviceDescription);
    }
}
