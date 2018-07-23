package javase.customAnnotation;

import org.springframework.context.support.EmbeddedValueResolutionSupport;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.util.Set;

/**
 * @author tangxiaoshuang
 * @date 2018/5/29 14:05
 */
public class MyDataFormatFoctory extends EmbeddedValueResolutionSupport
        implements AnnotationFormatterFactory<MyDataFormat> {
    @Override
    public Set<Class<?>> getFieldTypes() {
        return null;
    }

    @Override
    public Printer<?> getPrinter(MyDataFormat annotation, Class<?> fieldType) {
        return null;
    }

    @Override
    public Parser<?> getParser(MyDataFormat annotation, Class<?> fieldType) {
        return null;
    }
}
