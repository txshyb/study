package javase.designpattern.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangxiaoshuang
 * @date 2018/5/26 9:26
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("主线程开始");
        FutureClient futueClient = new FutureClient();
        Data data = futueClient.request();
        logger.info("do otherthing");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(data.getResponse());
        logger.info("keep doing");
    }
}
