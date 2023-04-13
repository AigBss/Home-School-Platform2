package com.example.homeschoolplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.homeschoolplatform")
public class HomeSchoolPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeSchoolPlatformApplication.class, args);
    }

}
