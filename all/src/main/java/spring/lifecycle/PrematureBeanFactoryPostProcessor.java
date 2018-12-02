package spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class PrematureBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.err.println("调用MyBeanFactoryPostProcessor的postProcessBeanFactory");
        //BeanFactoryPostProcessor的postProcessBeanFactory方法是在构造器之前执行
        //但是下面这行方法getBeansOfType强制拿到了bBean对象，会使Bean的构造器执行
//        Map<String, BBean> map = beanFactory.getBeansOfType(BBean.class);
//        for (BBean bBean : map.values()) {
//            bBean.setaBean(bean);
//            bBean.setName("BeanFactoryPostProcessors设置name值");
//        }
        //下面方法为指明具体类，所以bean的实例化（构造器）会在postProcessBeanFactory方法之后执行
        BeanDefinition bd = beanFactory.getBeanDefinition("bBean");
        //想要下面的getPropertyValues是获取的
        // <bean name="bBean" class="spring.lifecycle.BBean">
        //        <property name="name" value="bean定义"></property>
        //  </bean>
        //中的property  如果你不配做上面的name,aBean，则pv.contains("name")为false
        MutablePropertyValues pv =  bd.getPropertyValues();
        if (pv.contains("name")) {
            pv.addPropertyValue("name", "在BeanFactoryPostProcessor中修改之后的name信息");
        }
        System.err.println("调用MyBeanFactoryPostProcessor的postProcessBeanFactory结束");
    }
}