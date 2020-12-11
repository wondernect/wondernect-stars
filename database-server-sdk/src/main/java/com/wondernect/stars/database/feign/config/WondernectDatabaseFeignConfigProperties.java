package com.wondernect.stars.database.feign.config;

import com.wondernect.elements.property.source.WondernectPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2018, wondernect.com
 * FileName: SnowFlakeConfigProperties
 * Author: chenxun
 * Date: 2018/5/25 10:37
 * Description: file feign config
 */
@Component
@Primary
@PropertySource(value = {"classpath:application.properties", "classpath:application.yml", "classpath:application.yaml"}, ignoreResourceNotFound = true, factory = WondernectPropertySourceFactory.class)
@ConfigurationProperties(prefix = "wondernect.stars.database.feign")
public class WondernectDatabaseFeignConfigProperties implements Serializable {

    private static final long serialVersionUID = 2063732733149304087L;

    private String name = ""; // 远程服务name

    private String url = ""; // 远程服务url

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

