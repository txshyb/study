package tspring.ioc;

import tspring.Constant;

import javax.servlet.*;
import java.util.HashMap;
import java.util.Properties;

public class ContextLoaderListener implements ServletContextListener, ServletContextAttributeListener {

    private String path = "applicationContext.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载配置文件
        Properties properties = doLoadProperties(path);
        //扫包
        doScanPackage(properties.getProperty(Constant.scanPackage));
        //自动装配
        autowire();

        HashMap<String, BeanDefinition> beanDefinitions = Beans.getBeanDefinitions();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void autowire() {
        BeanAutowire.autowire();
    }

    private void doScanPackage(String packagPath) {
        PackageScan.scanPackage(packagPath);
    }

    private Properties doLoadProperties(String path) {
        return PropertiesLoader.loadProperties(path);
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {

    }
}
