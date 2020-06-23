package com.wondernect.stars.user.service;

import com.wondernect.stars.user.model.User;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserOpenAPI
 * Author: chenxun
 * Date: 2019/4/11 15:53
 * Description:
 */
public interface UserOpenService {

    /**
     * 初始化平台超级管理员用户&角色
     */
    User initPlatformAdmin();
}
