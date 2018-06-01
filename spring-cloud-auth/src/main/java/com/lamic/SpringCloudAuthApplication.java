package com.lamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringCloudAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAuthApplication.class, args);
    }
}
