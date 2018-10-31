package tspring.ioc;

import tspring.Constant;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties(String path) {
        Properties p = new Properties();
        try {
            InputStream resourceAsStream = PropertiesLoader.class.getClassLoader().getResourceAsStream(path);
            p.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<?> enumeration = p.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            if (!Constant.scanPackage.equalsIgnoreCase(name)) {
                String className = (String) p.get(name);
                BeanFactory.newInstance(name, className);
            }
        }
        return p;
    }
}
