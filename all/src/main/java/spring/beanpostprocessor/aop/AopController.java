package spring.beanpostprocessor.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/2 12:42
 * @desc:
 */
@Controller
public class AopController {

    @Autowired
    private AopService aopService;

    @RequestMapping("/aopTest")
    @ResponseBody
    public Object test() {
        aopService.test();
        return "aopTest";
    }


    @Override
    public String toString() {
        return "AopController{" +
                "aopService=" + aopService +
                '}';
    }
}
