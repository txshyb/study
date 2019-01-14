package convert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/31 13:50
 * @desc:
 */
@Controller
@RequestMapping("convertertest")
public class ConverterController {

    /**
     * 请求/convertertest/test?user=1,2,3  能收到user
     * @param user
     */
    @RequestMapping("test")
    public void Test(User user) {
        System.out.println(user);
    }

    @RequestMapping("test2")
    public void test2(String user) {
        System.out.println(user);
    }
}
