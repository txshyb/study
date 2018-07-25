import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Before;
import org.junit.Test;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/23 15:51
 * @desc:
 */
public class RoleTest extends BaseTest {

    @Test
    public void test() {
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.isPermitted("user:update"));
    }

    @Test
    public void test2() {
        Session session = subject.getSession(false);
        System.out.println(session);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(session);
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
