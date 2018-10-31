package tspring.ioc;

import java.lang.annotation.*;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/31 15:06
 * @desc:
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TRequestMapping {
}
