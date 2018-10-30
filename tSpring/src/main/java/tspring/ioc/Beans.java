package tspring.ioc;

import java.util.HashMap;

public class Beans {
    private static HashMap<String, BeanDefinition> beanDefinitions = new HashMap<>();

    public static void addBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitions.put(name, beanDefinition);
    }

    public static HashMap<String, BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }
}
