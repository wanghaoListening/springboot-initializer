package com.haothink.initializer.biz.dservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.haothink.initializer.api.dto.UserDTO;
import com.haothink.initializer.api.model.Result;
import com.haothink.initializer.api.service.UserDService;
import com.haothink.initializer.biz.bean.bos.UserBO;
import com.haothink.initializer.biz.service.UserService;
import com.haothink.initializer.biz.utils.CopyUtil;
import com.haothink.initializer.biz.utils.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * @author wanghao
 * @date 2019年06月21日 18:48
 * description:
 */
@Service(version="1.0.0")
public class UserDServiceImpl implements UserDService {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserDServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public Result addUser(UserDTO userDTO) {

        LOGGER.info("the service addOrgUser the param is {}",userDTO);
        try {
            Map<String,String> allBadField = HibernateValidator.volidateALL(userDTO);
            if(!allBadField.isEmpty()){
                LOGGER.error("the param field is illegal,{}",allBadField);
                return Result.buildFailedResult(allBadField.toString());
            }
            UserBO userBO = CopyUtil.copyToNewObject(userDTO,UserBO.class);
            boolean result = userService.addUser(userBO);
            if(result){
                //插入成功
                return Result.buildSuccessResult();
            }
            return Result.buildFailedResult("save a user record fail");
        }catch (Exception e){
            LOGGER.error("addOrgUser occur exception: {}",e);
            return Result.buildFailedResult(e.toString());
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public Result<UserDTO> getUserById(Long id) {

        LOGGER.info("the service getUserById the id is {}",id);
        try {
            if(Objects.isNull(id)){
                return Result.buildFailedResult("the id must be not null");
            }

            UserBO userBO = userService.getUserById(id);

            UserDTO userDTO = CopyUtil.copyToNewObject(userBO,UserDTO.class);
            return Result.buildSuccessResult();

        }catch (Exception e){
            LOGGER.error("addOrgUser occur exception: {}",e);
            return Result.buildFailedResult(e.toString());
        }
    }
}
