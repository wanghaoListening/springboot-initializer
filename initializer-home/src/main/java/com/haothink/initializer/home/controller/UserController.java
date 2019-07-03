package com.haothink.initializer.home.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.haothink.initializer.api.dto.UserDTO;
import com.haothink.initializer.api.model.Result;
import com.haothink.initializer.api.service.UserDService;

import com.haothink.initializer.home.beans.po.UserPO;
import com.haothink.initializer.home.beans.vo.UserVO;
import com.haothink.initializer.home.utils.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author wanghao
 * @date 2019年06月18日 19:17
 * description: 用户控制
 */

@RequestMapping("/user")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Reference(version="1.0.0")
    private UserDService userDService;


    @RequestMapping(value = "/loginIn")
    public Result loginIn(UserPO userPO){

        LOGGER.info("login in user by param {}",userPO);
        try {
            Result<UserDTO> result = userDService.getUserByName(userPO.getAccountName());
            if(!result.isSuccess() || Objects.isNull(result.getModule())){
                return Result.buildFailedResult("登录失败");
            }
            UserDTO userDTO = result.getModule();
            return Result.buildSuccessResult("登录成功");
        }catch (Exception e){
            LOGGER.error("login in user occur ex",e);
            return Result.buildFailedResult("登录失败");
        }
    }


    @RequestMapping("/signIn")
    public Result signInUser(UserPO userPO){

        LOGGER.info("sign in user by param {}",userPO);
        try {
            UserDTO userDTO = CopyUtil.copyToNewObject(userPO,UserDTO.class);
            Result result = userDService.addUser(userDTO);
            if(result.isSuccess()){
                return Result.buildSuccessResult("注册成功");
            }
            return Result.buildFailedResult("注册失败");
        }catch (Exception e){
            LOGGER.error("sign in user occur ex",e);
            return Result.buildFailedResult("注册失败");
        }
    }


    @GetMapping("/get")
    public Result acquireUser(Long id){

        LOGGER.info("acquire user by id {}",id);
        try {

            Result<UserDTO> result = userDService.getUserById(id);
            if(result.isSuccess()){
                UserVO userVO = CopyUtil.copyToNewObject(result.getModule(),UserVO.class);
                return Result.buildSuccessResult(userVO);
            }
            return Result.buildFailedResult("获取用户失败");
        }catch (Exception e){
            LOGGER.error("acquire user occur ex",e);
            return Result.buildFailedResult("获取用户失败");
        }
    }

    @GetMapping("/cookie")
    public String cookie(@RequestParam("browser") String browser, HttpServletRequest request, HttpSession session) {
        //取出session中的browser
        Object sessionBrowser = session.getAttribute("browser");
        if (sessionBrowser == null) {
            System.out.println("不存在session，设置browser=" + browser);
            session.setAttribute("browser", browser);
        } else {
            System.out.println("存在session，browser=" + sessionBrowser.toString());
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " : " + cookie.getValue());
            }
        }
        return "index";
    }

}
