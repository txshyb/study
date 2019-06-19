package com.txs.springboottest.autowire;

import java.lang.annotation.*;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:41
 * @desc:
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisStringAnn {
}
