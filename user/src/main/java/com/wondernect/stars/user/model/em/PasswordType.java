package com.wondernect.stars.user.model.em;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: PasswordType
 * Author: chenxun
 * Date: 2018/11/27 14:54
 * Description: 用户密码类型
 */
public enum PasswordType {

    USERNAME_PASSWORD, // 用户名密码登录

    GESTURE_PASSWORD, // 手势密码登录(苹果为指纹/安卓为九宫格手势)

    APPLE_PASSWORD, // 苹果短数字密码登录(九宫格数字)

    VOICE_PASSWORD, // 语音密码登录(语音翻译指定短语)
}

