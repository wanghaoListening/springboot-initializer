package com.haothink.initializer.home.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wanghao
 * @date 2019年07月16日 10:06
 * description: 在需要保证 接口幂等性 的Controller的方法上使用此注解
 *
 * 参考：https://cloud.tencent.com/developer/article/1446109
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiIdempotent {


}
