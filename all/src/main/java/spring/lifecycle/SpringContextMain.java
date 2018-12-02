package spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/2 10:27
 * @desc:
 */
public class SpringContextMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/beanpostprocessor.xml");
        BBean bBean = applicationContext.getBean("bBean", BBean.class);

        System.err.println(bBean.toString());
    }
}
