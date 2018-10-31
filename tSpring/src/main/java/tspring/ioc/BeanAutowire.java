package tspring.ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanAutowire {

    public static void autowire() {

        HashMap<String, BeanDefinition> beanDefinitions = Beans.getBeanDefinitions();

        for (Map.Entry<String,BeanDefinition> entry : beanDefinitions.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            Class clazz = beanDefinition.getClazz();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                TAutowire tAutowire = field.getAnnotation(TAutowire.class);
                if(tAutowire == null) {
                    continue;
                }
                // 取消属性的访问权限控制，即使private属性也可以进行访问。
                field.setAccessible(true);
                String fieldName = field.getName();
                try {
                    //为属性赋值
                    Object t = beanDefinitions.get(fieldName).getT();
                    field.set(beanDefinition.getT(),t);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
