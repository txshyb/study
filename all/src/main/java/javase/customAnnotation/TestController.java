package javase.customAnnotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tangxiaoshuang
 * @date 2018/5/29 14:13
 */
@Controller
public class TestController {

    @Autowired
    private TestService testService;
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @RequestMapping("/test")
    @Test
    public void test(Person person) {
        testService.test();
        logger.info(Thread.currentThread().getName());
        logger.info("{}",person);
    }
}
