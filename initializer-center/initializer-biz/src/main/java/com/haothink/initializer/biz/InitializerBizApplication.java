package com.haothink.initializer.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//开启定时任务,在需要跑任务的方法上加@Scheduled(cron="*/6 * * * * ?")
@EnableScheduling
@SpringBootApplication
@MapperScan("com.haothink.initializer.biz.dao")
public class InitializerBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitializerBizApplication.class, args);
    }

}
