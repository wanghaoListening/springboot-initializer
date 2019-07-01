package com.haothink.initializer.biz.service;

import com.haothink.initializer.biz.bean.bos.UserBO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

/**
 * @author wanghao
 * @date 2019年06月21日 18:38
 * description:
 */
public interface UserService {


    boolean addUser(UserBO userBO) throws InvalidKeySpecException, NoSuchAlgorithmException;

    UserBO getUserById(Long id);

    UserBO getUserByName(String accountName);
}
