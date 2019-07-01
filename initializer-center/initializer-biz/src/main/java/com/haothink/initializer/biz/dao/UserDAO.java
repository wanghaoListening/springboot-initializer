package com.haothink.initializer.biz.dao;


import com.haothink.initializer.biz.bean.dos.UserDO;

public interface UserDAO extends BaseDAO<UserDO> {


    UserDO selectByName(String accountName);
}