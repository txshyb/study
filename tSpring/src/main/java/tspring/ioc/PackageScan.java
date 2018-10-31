package tspring.ioc;

import java.io.File;
import java.io.FileFilter;

public class PackageScan {

    public static void scanPackage(String packagPath) {
        String path = PackageScan.class.getClassLoader().getResource("").getPath();
        String pack = packagPath.replace(".", File.separatorChar+"");
        File file = new File(path + File.separatorChar + pack);
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
            BeanFactory.newInstance(packagPath + "." + f.getName().replace(".class", ""));
        }
    }

    public static void main(String[] args) {
        System.out.println(PackageScan.class.getClassLoader().getResource("").getPath());
    }
}
