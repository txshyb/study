package tspring.ioc;

import java.io.File;
import java.io.FileFilter;

public class PackageScan {

    public static void scanPackage(String packagPath) {
        File file = new File(packagPath);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile() && pathname.getName().endsWith(".class")) {
                    return true;
                }
                return false;
            }
        });

        for (File f : files) {
            BeanFactory.newInstance(f.getName().replace(".class",""));
        }
    }
}
