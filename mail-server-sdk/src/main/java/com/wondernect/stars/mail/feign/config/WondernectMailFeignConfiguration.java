package com.wondernect.stars.mail.feign.config;

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
 * @Author:王威
 * @Date: 2020/11/26 11:41
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(WondernectMailFeignConfigProperties.class)
public class WondernectMailFeignConfiguration implements RequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(WondernectMailFeignConfigProperties.class);

    @Autowired
    private WondernectMailFeignConfigProperties wondernectRbacFeignConfigProperties;

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
            Object value = request.getAttribute(wondernectRbacFeignConfigProperties.getUserIdPropertyName());
            if (ESObjectUtils.isNotNull(value)) {
                requestTemplate.header(wondernectRbacFeignConfigProperties.getUserIdPropertyName(), value.toString());
            }
        }
        requestTemplate.header(wondernectRbacFeignConfigProperties.getAppIdPropertyName(), wondernectRbacFeignConfigProperties.getAppId());
        requestTemplate.header(wondernectRbacFeignConfigProperties.getAppSecretPropertyName(), wondernectRbacFeignConfigProperties.getAppSecret());
    }
}
