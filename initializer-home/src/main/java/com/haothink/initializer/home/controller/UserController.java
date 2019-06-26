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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Reference(version="1.0.0")
    private UserDService userDService;


    @PostMapping("/signIn")
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

}
