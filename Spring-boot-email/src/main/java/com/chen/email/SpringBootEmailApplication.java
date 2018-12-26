package com.chen.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chen.email.listener.PropertiesListener;

@SpringBootApplication
public class SpringBootEmailApplication {

	public static void main(String[] args) {

		// SpringApplication.run(SpringBootEmailApplication.class, args);
		SpringApplication application = new SpringApplication(SpringBootEmailApplication.class);
		// 注册监听器----也可以不配置，直接启动；
		application.addListeners(new PropertiesListener("application-email.properties"));
		application.run(args);
	}

}
