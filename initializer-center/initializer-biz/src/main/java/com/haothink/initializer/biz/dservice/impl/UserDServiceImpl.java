package com.haothink.initializer.biz.dservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.haothink.initializer.api.dto.UserDTO;
import com.haothink.initializer.api.model.Result;
import com.haothink.initializer.api.service.UserDService;

import java.util.ArrayList;

/**
 * @author wanghao
 * @date 2019年06月21日 18:48
 * description:
 */
@Service(version="1.0.0")
public class UserDServiceImpl implements UserDService {


    @Override
    public Result addUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public Result<ArrayList<UserDTO>> getUserById(Long id) {
        return null;
    }
}
