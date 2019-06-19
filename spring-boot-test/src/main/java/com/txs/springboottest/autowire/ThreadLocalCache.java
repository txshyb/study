package com.txs.springboottest.autowire;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:16
 * @desc:
 */
public class ThreadLocalCache {
    public static ThreadLocal<RedisString> threadLocal = new ThreadLocal<>();
}
