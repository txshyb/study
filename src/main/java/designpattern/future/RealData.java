package designpattern.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tangxiaoshuang
 * @date 2018/5/26 9:27
 */
public class RealData implements Data {

    private static Logger logger = LoggerFactory.getLogger(RealData.class);
    public RealData() {
        logger.info("耗时操作");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("耗时操作结束");
    }

    @Override
    public String getResponse() {
        return "dgggjkgjfs";
    }
}
