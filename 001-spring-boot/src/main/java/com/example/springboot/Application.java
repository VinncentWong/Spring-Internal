package com.example.springboot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		var context = new AnnotationConfigApplicationContext(UserConfiguration.class);
		// or you can use base packages scan to prevent some component scan
		var basePackages = "com.example.springboot";
		var context = new AnnotationConfigApplicationContext(basePackages);

		// get the pojo object
		var user = context.getBean("userCentWong", User.class);

		System.out.println(user.name.equals("CentWong")); // TRUE

		System.out.println(user.hostName.equals("spring-app-by-centwong")); // TRUE

		/*
		************
		CENTWONG
		************
		 */
		System.out.println(user.namePattern);

		System.out.println(user.environment);

		var configuration = context.getBean(UserConfiguration.class);

		System.out.println("Publishing event");
		configuration.publishEvent();
	}

}
