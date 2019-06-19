package com.txs.springboottest.autowire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:27
 * @desc:
 */
@Controller @Order(2)
public class AutowireController {

    @Autowired
    private RedisString redisString;

    @RequestMapping("/autowire")
    @ResponseBody
    @RedisStringAnn
    public String autowire(Integer id) {
        return redisString.test();
    }
}
