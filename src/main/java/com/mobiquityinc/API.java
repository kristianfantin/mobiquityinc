package com.mobiquityinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.mobiquityinc")
@EnableAutoConfiguration
public class API {

    public static void main(String[] args) {
        SpringApplication.run(API.class);
    }

}
