package com.txs.springboottest.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 18:20
 * @desc:
 */
@Component
public class TestThread2 implements Runnable {

    @Autowired
    private AutowireController autowireController;
    @Override
    public void run() {

        while (true) {
            System.out.println("id2  " + autowireController.autowire(2));
        }
    }
}
