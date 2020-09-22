package com.wondernect.stars.file.server.config;

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
@EnableConfigurationProperties(FileConfigProperties.class)
public class FileConfig {
    /**
     * 该配置使用@EnableConfigurationProperties(FileConfigProperties.class)使FileConfigProperties配置生效
     */
}
