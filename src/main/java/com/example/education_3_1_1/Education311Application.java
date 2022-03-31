package com.example.education_3_1_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan (basePackages = {"com.example.education_3_1_1.controller"})
public class Education311Application {

    public static void main(String[] args) {
        SpringApplication.run(Education311Application.class, args);
    }

}
