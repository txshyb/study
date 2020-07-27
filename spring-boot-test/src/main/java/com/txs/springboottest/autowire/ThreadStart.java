package com.txs.springboottest.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 18:21
 * @desc:
 *
 */
//@Component
public class ThreadStart {
    //因为是RedisString的具体实现是根据线程来的 并且获取存储过程中没有共享变量 不会产生线程安全问题
    @Autowired TestThread testThread;
    @Autowired TestThread2 testThread2;
    @PostConstruct
    public void init() {
        new Thread(testThread).start();
        new Thread(testThread2).start();
    }
}
