package com.example.MiddleAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MiddleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddleApiApplication.class, args);
	}

}
