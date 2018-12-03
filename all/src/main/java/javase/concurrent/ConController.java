package javase.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/3 14:41
 * @desc:
 */
@Controller
public class ConController {

    @Autowired
    private ConService conServiceImpl1;

    @Autowired
    private ConService conServiceImpl2;

    @Autowired
    private ConFacade conFacade;

    @RequestMapping("/conService1")
    @ResponseBody
    public Object conService1() {
        conFacade.setConService(conServiceImpl1);
        boolean flag = true;
        while (flag) {
            System.out.println("conService1" + conFacade.getString());
        }
        return "1";
    }

    @RequestMapping("/conService2")
    @ResponseBody
    public Object conService2() {
        conFacade.setConService(conServiceImpl2);
        boolean flag = true;
        while (flag) {
            System.out.println("conService2" + conFacade.getString());
        }
        return "1";
    }

}
