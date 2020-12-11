package com.wondernect.stars.mail.feign.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WondernectMailFeignConfigProperties.class)
public class WondernectMailFeignConfiguration {

}
