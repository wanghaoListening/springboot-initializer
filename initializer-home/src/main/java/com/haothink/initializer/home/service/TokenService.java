package com.haothink.initializer.home.service;

import com.haothink.common.domain.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghao
 * @date 2019年07月16日 10:11
 * description:
 */
public interface TokenService {

    public Result createToken();

    public void checkToken(HttpServletRequest request);
}
