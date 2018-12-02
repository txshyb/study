package spring.lifecycle;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class BBean implements InitializingBean{

    public BBean() {
        System.err.println("BBean构造器执行");
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PostConstruct
    public void postConstruct() {
        System.err.println("调用postConstruct");
    }

    public void init() {
        System.err.println("调用init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.err.println("调用InitializingBean的afterPropertiesSet");;
    }

    @Override
    public String toString() {
        return "BBean{" +
                "name='" + name + '\'' +
                '}';
    }
}