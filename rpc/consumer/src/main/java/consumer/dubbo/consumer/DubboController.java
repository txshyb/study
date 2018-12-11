package consumer.dubbo.consumer;

import consumer.dubbo.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/8 21:17
 * @desc:
 */
@Controller
public class DubboController {

    /**
     * TODO   https://blog.csdn.net/zhou_java_hui/article/details/53039491
     * @Reference失效问题   该注解不能放在mvc扫包的类里
     */
//    @Reference
//    private DubboService dubboService;

    @Autowired
    private Test test;


    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {

//        String res =  dubboService.hello("花亚冰");
//        System.out.println("结果是" + res);
        System.out.println("结果是"+test.h());
        return "ss";
    }
}
