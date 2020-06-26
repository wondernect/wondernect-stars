package com.wondernect.stars.rbac.config;

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
@EnableConfigurationProperties(RBACConfigProperties.class)
public class RBACConfig {

    /**
     * 该配置使用@EnableConfigurationProperties(RBACConfigProperties.class)使RBACConfigProperties配置生效
     */
}
