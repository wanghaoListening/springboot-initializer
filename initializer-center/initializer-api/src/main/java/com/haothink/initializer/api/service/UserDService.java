package com.haothink.initializer.api.service;


import com.haothink.initializer.api.dto.UserDTO;
import com.haothink.initializer.api.model.Result;

import java.util.ArrayList;

/**
 * @author wanghao
 * @date 2019年06月18日 19:09
 * description: 用户服务的dubbo服务
 */

public interface UserDService {


    Result addUser(UserDTO userDTO);

    Result<UserDTO> getUserById(Long id);

    Result<UserDTO> getUserByName(String username);
}
