package com.haothink.initializer.biz.service.impl;

import com.haothink.common.password.PasswordHash;
import com.haothink.common.utils.CopyUtil;
import com.haothink.initializer.biz.bean.bos.UserBO;
import com.haothink.initializer.biz.bean.dos.UserDO;
import com.haothink.initializer.biz.dao.UserDAO;
import com.haothink.initializer.biz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author wanghao
 * @date 2019年06月21日 18:38
 * description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public boolean addUser(UserBO userBO) throws InvalidKeySpecException, NoSuchAlgorithmException {
        UserDO userDO = CopyUtil.copyToNewObject(userBO,UserDO.class);
        userDO.setPassword(PasswordHash.createHash(userDO.getPassword()));
        int result = userDAO.insertSelective(userDO);
        return (result >=1);
    }

    @Override
    public UserBO getUserById(Long id) {
        UserDO userDO = userDAO.selectByPrimaryKey(id);
        return CopyUtil.copyToNewObject(userDO,UserBO.class);
    }

    @Override
    public UserBO getUserByName(String accountName) {

        UserDO userDO = userDAO.selectByName(accountName);
        return CopyUtil.copyToNewObject(userDO,UserBO.class);
    }
}
