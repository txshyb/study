package consumer.dubbo.controller;

import consumer.dubbo.model.User;
import consumer.dubbo.service.TxTestService;
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
@RequestMapping("/txTest")
public class TxTestController {

    @Autowired
    private TxTestService txTestService;


    @RequestMapping("/txTest")
    @ResponseBody
    public String txTest() {
        User user = new User("小明","123");
        txTestService.addUser(user);
        return "ss";
    }
}
