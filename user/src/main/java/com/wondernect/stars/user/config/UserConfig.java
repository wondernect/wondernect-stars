package com.wondernect.stars.user.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: UserConfig
 * Author: chenxun
 * Date: 2019/3/18 9:21
 * Description: user config
 */
@Configuration
@EnableConfigurationProperties(UserConfigProperties.class)
public class UserConfig {

    /**
     * 该配置使用@EnableConfigurationProperties(UserConfigProperties.class)使UserConfigProperties配置生效
     */
}
