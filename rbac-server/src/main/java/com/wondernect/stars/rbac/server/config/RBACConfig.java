package com.wondernect.stars.rbac.server.config;

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
@EnableConfigurationProperties(RBACConfigProperties.class)
public class RBACConfig {
    /**
     * 该配置使用@EnableConfigurationProperties(RBACConfigProperties.class)使RBACConfigProperties配置生效
     */
}
