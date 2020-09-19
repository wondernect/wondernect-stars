package com.wondernect.stars.app.server;

import com.wondernect.elements.boot.application.WondernectBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
