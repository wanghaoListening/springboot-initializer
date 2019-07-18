package com.haothink.initializer.home.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

/**
 * @author wanghao
 * @date 2019年07月18日 17:55
 * description:
 *
 * Spring RestTemplate经常被用作客户端向Restful API发送各种请求，也许你也碰到过这种需求，很多请
 * 求都需要用到相似或者相同的Http Header。如果在每次请求之前都把Header填入HttpEntity/RequestEntity，
 * 这样的代码会显得十分冗余。
 * Spring提供了ClientHttpRequestInterceptor接口，可以对请求进行拦截，并在其被发送至服务端之前修改请
 * 求或是增强相应的信息。
 */
@Component
public class ActionTrackInterceptor implements ClientHttpRequestInterceptor {


    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        HttpHeaders headers = httpRequest.getHeaders();

        // 加入自定义字段
        headers.add("actionId", UUID.randomUUID().toString());

        // 保证请求继续被执行
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
