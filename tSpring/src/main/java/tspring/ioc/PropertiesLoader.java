package tspring.ioc;

import tspring.Constant;

import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties(String path) {
        Properties p = new Properties();
        try {
            p.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Enumeration<?> enumeration = p.propertyNames();
        while (enumeration.hasMoreElements()) {
            String name = (String)enumeration.nextElement();
            if(name != Constant.scanPackage) {
                String className = (String)p.get(name);
                BeanFactory.newInstance(name,className);
            }
        }
        return p;
    }
}
