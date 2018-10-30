package tspring.servlet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/30 11:16
 * @desc: 控制层适配
 */
public class HandlerAdaper<T> {

    //类
    private T t;
    //方法名
    private String method;
    //请求类型 GET、POST
    private String type;

    //TODO 方法参数封装


    public Object invoke() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<?> clazz = getT().getClass();
        Method method = clazz.getMethod(getMethod(), null);
        return method.invoke(t, null);
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
