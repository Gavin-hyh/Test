package com.hyh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hyh.mapper")
@SpringBootApplication
public class FiveApp {
    public static void main(String[] args) {
        SpringApplication.run(FiveApp.class);
    }
}
