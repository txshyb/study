package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/2 11:10
 * @desc:
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("BeanPostProcessor的postProcessBeforeInitialization     " + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.err.println("BeanPostProcessor的postProcessAfterInitialization      "+bean);
        return bean;
    }
}
