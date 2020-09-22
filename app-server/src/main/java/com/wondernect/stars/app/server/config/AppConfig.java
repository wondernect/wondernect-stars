package com.wondernect.stars.app.server.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2020, wondernect.com
 * FileName: RBACConfig
 * Author: chenxun
 * Date: 2020-09-20 10:09
 * Description:
 */
@Configuration
@EnableConfigurationProperties(AppConfigProperties.class)
public class AppConfig {
    /**
     * 该配置使用@EnableConfigurationProperties(AppConfigProperties.class)使AppConfigProperties配置生效
     */
}
