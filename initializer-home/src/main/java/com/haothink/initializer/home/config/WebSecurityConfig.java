package com.haothink.initializer.home.config;

import com.haothink.initializer.home.utils.HashPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author wanghao
 * @date 2019年06月27日 20:02
 * description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;



    @Override
    protected UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                //允许所有用户访问"/"和"/user/signIn" 注册页
                 http.authorizeRequests()
                         .antMatchers(HttpMethod.POST, "/","/user/signIn").permitAll()
                         // 其他地址的访问均需验证权限
                         .anyRequest().authenticated()
                         .and()
                         .formLogin()
                         //登录接口
                         .loginPage("/user/loginIn")
                         .failureUrl("/user/error").permitAll()
                         .and()
                         .logout()
                         .permitAll()
                         .logoutSuccessUrl("/user/cookie");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 配置忽略js、css、images静态文件
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new HashPasswordEncoder();
    }

}
