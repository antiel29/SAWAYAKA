package com.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition
@ComponentScan(basePackages = "com.api")
public class SawayakaApp {
    public static void main(String[] args) {

        SpringApplication.run(SawayakaApp.class,args);
    }
}