package com.lamic.springcloudzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
//@EnableZipkinStreamServer
public class SpringCloudZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZipkinApplication.class, args);
	}
}
