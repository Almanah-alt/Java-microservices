package com.example.centerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CenterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterServiceApplication.class, args);
    }

}
