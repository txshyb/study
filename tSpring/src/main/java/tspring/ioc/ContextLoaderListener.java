package tspring.ioc;

import tspring.Constant;

import javax.servlet.*;
import java.util.Properties;

public class ContextLoaderListener implements ServletContextListener, ServletContextAttributeListener {

    private String path = "classpath:applicationContext.properties";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        //加载配置文件
        Properties properties = doLoadProperties(path);
        //扫包
        doScanPackage(properties.getProperty(Constant.scanPackage));
        //自动装配
        autowire();
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
}
