package com.txs.springboottest.autowire;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

import java.io.Serializable;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:09
 * @desc: 该动态注入是按spring动态注入HttpServletRequest实现方式实现的，获取的是动态代理
 * 注入时流程
 *    --->DefaultListableBeanFactory.findAutowireCandidates
 *      --->	public static Object resolveAutowiringValue(Object autowiringValue, Class<?> requiredType) {
                    if (autowiringValue instanceof ObjectFactory && !requiredType.isInstance(autowiringValue)) {
                        ObjectFactory<?> factory = (ObjectFactory<?>) autowiringValue;
                        if (autowiringValue instanceof Serializable && requiredType.isInterface()) {
                            autowiringValue = Proxy.newProxyInstance(requiredType.getClassLoader(),
                            new Class<?>[] {requiredType}, new ObjectFactoryDelegatingInvocationHandler(factory));
                        }
                    else {
                        return factory.getObject();
                        }
                    }
                    return autowiringValue;
                }
 *
 */
public class RedisStringObjectFactory implements ObjectFactory<RedisString>, Serializable {
    @Override
    public RedisString getObject() throws BeansException {
        return ThreadLocalCache.threadLocal.get();
    }
}
