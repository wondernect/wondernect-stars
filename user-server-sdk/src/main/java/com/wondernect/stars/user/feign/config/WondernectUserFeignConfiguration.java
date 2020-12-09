package com.wondernect.stars.user.feign.config;

import com.wondernect.elements.common.utils.ESObjectUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Copyright (C), 2017-2019, wondernect.com
 * FileName: FeignConfiguration
 * Author: chenxun
 * Date: 2019/6/12 15:21
 * Description:
 */
@Configuration
@EnableConfigurationProperties(WondernectUserFeignConfigProperties.class)
public class WondernectUserFeignConfiguration implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WondernectUserFeignConfiguration.class);

    @Autowired
    private WondernectUserFeignConfigProperties wondernectUserFeignConfigProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ESObjectUtils.isNotNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> enumeration = request.getHeaderNames();
            if (ESObjectUtils.isNotNull(enumeration)) {
                while (enumeration.hasMoreElements()) {
                    String name = enumeration.nextElement();
                    String value = request.getHeader(name);
                    requestTemplate.header(name, value);
                }
            }
            Object requestId = request.getAttribute(wondernectUserFeignConfigProperties.getRequestIdPropertyName());
            if (ESObjectUtils.isNotNull(requestId)) {
                requestTemplate.header(wondernectUserFeignConfigProperties.getRequestIdPropertyName(), requestId.toString());
            }
            Object userId = request.getAttribute(wondernectUserFeignConfigProperties.getUserIdPropertyName());
            if (ESObjectUtils.isNotNull(userId)) {
                requestTemplate.header(wondernectUserFeignConfigProperties.getUserIdPropertyName(), userId.toString());
            }
            requestTemplate.header(wondernectUserFeignConfigProperties.getAppIdPropertyName(), wondernectUserFeignConfigProperties.getAppId());
            requestTemplate.header(wondernectUserFeignConfigProperties.getAppSecretPropertyName(), wondernectUserFeignConfigProperties.getAppSecret());
        }
    }
}
