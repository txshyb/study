package com.txs.springboottest.autowire;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

import java.io.Serializable;

/**
 * @author: tangxiaoshuang
 * @date: 2019/6/19 16:09
 * @desc:
 ** ObjectFactory<RedisString> 注入的是RedisString的代理对象
 *  * 正在执行到RedisString的具体方法时ObjectFactory#getObject()才会被执行到
 *
 * 该动态注入是按spring动态注入HttpServletRequest实现方式实现的，获取的是动态代理
 * WebApplicationContextUtils#registerWebApplicationScopes()中有
 * 		beanFactory.registerResolvableDependency(ServletRequest.class, new RequestObjectFactory());
 * 		beanFactory.registerResolvableDependency(ServletResponse.class, new ResponseObjectFactory());
 * 		beanFactory.registerResolvableDependency(HttpSession.class, new SessionObjectFactory());
 * 		beanFactory.registerResolvableDependency(WebRequest.class, new WebRequestObjectFactory());
 *
 * 即为容器增加了四个class对应的bean（其中ServletRequest 为RequestObjectFactory)
 * 再bean注入servletrequest时流程，（其中走到if的那个分支不确定，但是每个分支都调用了factory.getObject()，第一个分支在代理中调了）
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
 * RequestObjectFactory代码
 *        @Override
 *        public ServletRequest getObject() {
 *          //currentRequestAttributes() 方法是通过ThreadLocal实现（在http请求时放入ThreadLocal中的）
 * 			return currentRequestAttributes().getRequest();
 *        }
 */
public class RedisStringObjectFactory implements ObjectFactory<RedisString>, Serializable {
    @Override
    public RedisString getObject() throws BeansException {
        return ThreadLocalCache.threadLocal.get();
    }
}
