package com.haothink.initializer.biz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wanghao
 * @date 2019年06月17日 19:49
 * description:
 */
@Configuration
@MapperScan("com.haothink.initializer.biz.dao")
public class MyBatisConfig {



}
