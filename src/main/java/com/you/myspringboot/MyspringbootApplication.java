package com.you.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.you.myspringboot.mapper")
public class MyspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyspringbootApplication.class, args);
    }

}
