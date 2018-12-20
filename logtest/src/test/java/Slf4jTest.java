import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: tangxiaoshuang
 * @date: 2018/12/18 15:01
 * @desc:
 */

public class Slf4jTest {

    @Test
    public void testSlf4j() {
        Logger logger = LoggerFactory.getLogger(Slf4jTest.class);
        logger.error("123");
    }
}
