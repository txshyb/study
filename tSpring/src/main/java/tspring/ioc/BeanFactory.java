package tspring.ioc;

import sun.reflect.annotation.AnnotationType;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BeanFactory {

    private static ClassLoader classLoader;

    private static List<Type> annotations;

    static {

        classLoader = BeanFactory.class.getClassLoader();

        annotations = new ArrayList<>();
        annotations.add(TController.class);
        annotations.add(TService.class);
    }

    /**
     * 配置文件初始化对象
     *
     * @param name
     * @param className
     */
    public static void newInstance(String name, String className) {
        try {
            Beans.addBeanDefinition(name, newBeanDefinition(name, className));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static BeanDefinition newBeanDefinition(String name, String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Object o = clazz.newInstance();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setAliasName(name);
        beanDefinition.setClassName(className);
        beanDefinition.setClazz(clazz);
        beanDefinition.setT(o);
        return beanDefinition;
    }

    /**
     * 注解初始化对象
     *
     * @param className
     */
    public static void newInstance(String className) {
        try {
            BeanDefinition beanDefinition = newBeanDefinition(className);
            if (beanDefinition == null) {
                return;
            }
            int last = className.lastIndexOf(".");
            Beans.addBeanDefinition(className.substring(last+1, last + 2).toLowerCase() + className.substring(last + 2, className.length()), beanDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static BeanDefinition newBeanDefinition(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<?> clazz = classLoader.loadClass(className);
        Annotation[] annos = clazz.getAnnotations();

        //没有注解的说明不是spring管理的类
        for (Annotation annotation : annos) {
            Type aClass = annotation.annotationType();
            if (BeanFactory.annotations.contains(aClass)) {
                break;
            }
            return null;
        }

        Object o = clazz.newInstance();
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setAliasName(className.substring(className.lastIndexOf("."), className.length()));
        beanDefinition.setClassName(className);
        beanDefinition.setClazz(clazz);
        beanDefinition.setT(o);
        return beanDefinition;
    }
}
