package com.haothink.initializer.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//开启异步，只需要在需要执行异步的方法上添加@Async既可
@EnableAsync
@EnableRedisHttpSession
@SpringBootApplication
public class InitializerHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializerHomeApplication.class, args);
    }

}
