package spring.mvc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.entity.vo.Result;


/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/24 14:29
 * @desc:
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("login")
    public String login(String name, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token); //此处会走到Realm里的认证方法里
        } catch (Exception e) {
            logger.info("用户名或密码错误");
            return "fail";
        }
        return "index";
    }
}
