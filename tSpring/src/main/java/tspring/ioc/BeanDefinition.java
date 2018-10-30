package tspring.ioc;

public class BeanDefinition<T> {

    private T t;

    private Class<T> clazz;

    private String className;

    private String aliasName;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}
