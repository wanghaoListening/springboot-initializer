package com.haothink.controller;

import com.haothink.model.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wanghao
 * @date 2019年06月18日 19:17
 * description: 用户控制
 */

@RequestMapping("/user")
@RestController
public class UserController {


    @RequestMapping("/signIn")
    public Result signInUser(String name,String password,String email){

        return null;
    }
}
