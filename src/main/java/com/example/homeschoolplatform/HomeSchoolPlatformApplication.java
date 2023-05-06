package com.example.homeschoolplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@MapperScan(value = "com.example.homeschoolplatform.mapper")
public class HomeSchoolPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeSchoolPlatformApplication.class, args);
    }

}
