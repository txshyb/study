import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/23 14:45
 * @desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class BaseTest {


    protected Subject subject;
    protected UsernamePasswordToken token;

    @Before
    public void before() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-session.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        subject = SecurityUtils.getSubject();
        token = new UsernamePasswordToken("zhang", "123");
        subject.login(token);
        System.out.println("登录");
    }

    @After
    public void after() {
        Session session1 = subject.getSession(false);
        System.out.println(session1);
        subject.logout();
        System.out.println("登出");
        Session session = subject.getSession(false);
        System.out.println(session);
    }
}
