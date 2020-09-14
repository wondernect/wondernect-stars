package com.wondernect.stars.app.config;

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
@EnableConfigurationProperties(AppConfigProperties.class)
public class AppConfig {

    /**
     * 该配置使用@EnableConfigurationProperties(AppConfigProperties.class)使AppConfigProperties配置生效
     */
}
