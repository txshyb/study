package com.txs.springboottest.动态实例化;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller {


    @RequestMapping("/setObject")
    @ResponseBody
    public Object setObject() {
        BeanFactory beanFactory = BeanFactoryHolder.getBeanFactory();
        BeanDefinitionBuilder beanMer = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory)beanFactory;
        //动态注册类
        defaultListableBeanFactory.registerSingleton("user", new User(10,"xiao"));
        defaultListableBeanFactory.registerSingleton("user2", new User(12,"花收到就好"));
//        defaultListableBeanFactory.registerBeanDefinition("user", beanMer.getRawBeanDefinition());
        return 1;
    }

    @RequestMapping("/getObject")
    @ResponseBody
    public Object getObject() {
        //动态获取类
        User user1 = (User)ApplicationHolder.getApplication().getBean("user");
        User user2= (User)ApplicationHolder.getApplication().getBean("user2");
        User user3 = (User)ApplicationHolder.getApplication().getBean(User.class); // error    expected single matching bean but found 2: user,user2
        System.out.printf(user2.toString());
        System.out.printf(user3.toString());
        return 2;
    }
}
