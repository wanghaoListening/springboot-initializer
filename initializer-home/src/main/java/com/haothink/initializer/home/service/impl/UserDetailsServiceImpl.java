package com.haothink.initializer.home.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.haothink.initializer.api.dto.UserDTO;
import com.haothink.initializer.api.model.Result;
import com.haothink.initializer.api.service.UserDService;
import com.haothink.initializer.home.beans.UserInfo;
import com.haothink.initializer.home.utils.CopyUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author wanghao
 * @date 2019年07月01日 19:00
 * description:
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference(version="1.0.0")
    private UserDService userDService;


    /**
     * 提供一种从用户名可以查到用户并返回的方法
     * @param accountName
     *        账号名
     * @return
     *        用户详细
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {

        Result<UserDTO> result = userDService.getUserByName(accountName);
        if(!result.isSuccess() || Objects.isNull(result.getModule())){

            return null;
        }

        return CopyUtil.copyToNewObject(result.getModule(),UserInfo.class);
    }
}
