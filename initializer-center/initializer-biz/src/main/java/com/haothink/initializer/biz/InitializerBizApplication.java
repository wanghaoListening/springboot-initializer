package com.haothink.initializer.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.haothink.initializer.biz.dao")
public class InitializerBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializerBizApplication.class, args);
    }

}
