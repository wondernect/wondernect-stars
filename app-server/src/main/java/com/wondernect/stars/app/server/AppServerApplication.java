package com.wondernect.stars.app.server;

import com.wondernect.elements.boot.application.WondernectBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
// @EnableFeignClients(
// 		basePackages = "com.wondernect.*"
// )
@EntityScan(basePackages = {
		"com.wondernect.*"
})
@EnableJpaRepositories(basePackages = {
		"com.wondernect.*"
})
@SpringBootApplication
public class AppServerApplication extends WondernectBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppServerApplication.class, args);
	}

	@Override
	public void initAfterBootFinished() {
		System.out.println("App server start success !!!");
	}
}
