package convert;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public String Test(User user) {
        System.out.println(user);
        return "呵呵哈哈哈";
    }

    @RequestMapping("test2")
    @ResponseBody
    public String test2(String user) {
        System.out.println(user);
        return "呵呵哈哈哈";
    }
}
