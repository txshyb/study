package tspring.ioc;

public class BeanDefinition<T> {

    private T t;

    private String className;

    private String aliasName;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
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
