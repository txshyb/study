package com.txs.springboottest.autowire;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 18:10
 * @desc:
 */
@Component
public class RedisBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("=================================================");
        beanFactory.registerResolvableDependency(RedisString.class, new RedisStringObjectFactory());
    }
}
