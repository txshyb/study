package javase.customAnnotation;

import java.lang.annotation.*;

/**
 * @author tangxiaoshuang
 * @date 2018/5/29 14:02
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface MyDataFormat {
}
