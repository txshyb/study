package com.txs.springboottest.jmx;

import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2019/1/8 14:31
 * @desc:
 */
@Component
public class Person {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
