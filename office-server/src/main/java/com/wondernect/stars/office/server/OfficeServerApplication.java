package com.wondernect.stars.office.server;

import com.wondernect.elements.boot.application.WondernectBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.wondernect.*"
)
@EntityScan(basePackages = {
        "com.wondernect.*"
})
@EnableJpaRepositories(basePackages = {
        "com.wondernect.*"
})
@SpringBootApplication
public class OfficeServerApplication extends WondernectBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficeServerApplication.class, args);
    }

    @Override
    public void initAfterBootFinished() {
        System.out.println("Office server start success !!!");
    }
}
