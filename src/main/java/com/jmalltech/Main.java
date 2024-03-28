package com.jmalltech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(basePackages = {"com.jmalltech.mapper"})
@EnableCaching
@SpringBootApplication
//@EnableDiscoveryClient
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hello world!");
    }
}
