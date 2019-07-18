package com.haothink.initializer.home.service.impl;


import com.haothink.common.domain.Result;
import com.haothink.initializer.home.service.TokenService;
import com.haothink.initializer.home.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author wanghao
 * @date 2019年07月16日 10:10
 * description:
 */
@Service
public class TokenServiceImpl implements TokenService {


    private static final String TOKEN_NAME = "_token";

    private static final String TOKEN_PREFIX = "qf_org_";

    private static final long TOKEN_EXPIRE_TIME = 60*3;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public Result createToken() {

        String uuid =  UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        token.append(TOKEN_PREFIX).append(uuid);
        redisUtil.set(token.toString(),token.toString(),TOKEN_EXPIRE_TIME);
        return Result.buildSuccessResult(toString());
    }

    @Override
    public void checkToken(HttpServletRequest request) {

        String token = request.getHeader(TOKEN_NAME);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isBlank(token)) {
                throw new RuntimeException("请求头缺少_token param");
            }
        }

        if (!redisUtil.hasKey(token)) {
            throw new RuntimeException("请勿重新提交");
        }else{

            redisUtil.del(token);
        }

    }
}
