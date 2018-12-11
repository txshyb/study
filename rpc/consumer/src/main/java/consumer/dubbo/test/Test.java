package consumer.dubbo.test;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;
import provider.dubbo.DubboService;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/8 23:03
 * @desc:
 */
@Component
public class Test {

    @Reference(async = true)
    DubboService dubboService;

    public String  h()  {
        return dubboService.hello("hhhh");
    }
}
