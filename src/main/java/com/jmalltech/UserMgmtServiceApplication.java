package com.jmalltech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
//@ComponentScan(basePackages = {"com"})
@MapperScan(basePackages = {"com.jmalltech.mapper"})
@SpringBootApplication
public class UserMgmtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserMgmtServiceApplication.class, args);
    }
}