package spring.beanpostprocessor.aop;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/2 12:41
 * @desc:
 */
@Component
public class ProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {

        if(bean instanceof AopService) {
            // aop获取所有advice 之后返回其代理对象
            return Proxy.newProxyInstance(AopService.class.getClassLoader(), new Class[]{AopService.class},new InvocationHandler(){

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.err.println("proxy 执行逻辑");
                    return method.invoke(bean,args);
                }
            });
        }
        return bean;
    }
}
