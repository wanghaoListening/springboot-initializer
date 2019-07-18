package com.haothink.initializer.home.config;

import com.haothink.common.domain.Result;
import com.haothink.initializer.home.ex.HaothinkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghao
 * @date 2019年07月15日 10:16
 * description: 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(HttpServletRequest req, Exception e){

        if(e instanceof HaothinkException){
            //自定义处理
            return Result.buildFailedResult(e.getMessage());
        }
        return Result.buildFailedResult("服务异常");
    }

}
