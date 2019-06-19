package com.txs.springboottest.autowire;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:11
 * @desc:
 */
public class RedisStringImpl2 implements RedisString {
    @Override
    public String test() {
        return "RedisStringImpl2";
    }
}
