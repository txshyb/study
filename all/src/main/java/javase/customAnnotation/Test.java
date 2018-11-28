package javase.customAnnotation;

import java.lang.annotation.*;

/**
 * @author: tangxiaoshuang
 * @date: 2018/11/1 18:04
 * @desc:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Test {
}
