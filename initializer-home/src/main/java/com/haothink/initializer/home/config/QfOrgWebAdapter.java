package com.haothink.initializer.home.config;


import com.haothink.initializer.home.interceptor.ActionTrackInterceptor;
import com.haothink.initializer.home.interceptor.ApiIdempotentInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * @author wanghao
 * @date 2019年07月18日 17:40
 * description:
 */
@Configuration
public class QfOrgWebAdapter implements WebMvcConfigurer {

    @Autowired
    ActionTrackInterceptor actionTrackInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }

    /**
     * 声明为Bean，方便应用内使用同一实例
     * @return
     *   在需要用到的地方直接restTemplate即可使用
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // 把自定义的ClientHttpRequestInterceptor添加到RestTemplate，可添加多个
        restTemplate.setInterceptors(Collections.singletonList(actionTrackInterceptor));
        return restTemplate;
    }
}
