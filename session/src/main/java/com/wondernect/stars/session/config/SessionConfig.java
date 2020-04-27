package com.wondernect.stars.session.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: SessionConfig
 * Author: chenxun
 * Date: 2019/3/18 9:21
 * Description: session config
 */
@Configuration
@EnableConfigurationProperties(SessionConfigProperties.class)
public class SessionConfig {

    /**
     * 该配置使用@EnableConfigurationProperties(SessionConfigProperties.class)使SessionConfigProperties配置生效
     */
}
