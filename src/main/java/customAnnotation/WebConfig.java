package customAnnotation;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author tangxiaoshuang
 * @date 2018/5/29 14:10
 */
@Configurable
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        MyDataFormatFoctory annoFormater =new MyDataFormatFoctory();
        registry.addFormatterForFieldAnnotation(annoFormater);
    }
}
