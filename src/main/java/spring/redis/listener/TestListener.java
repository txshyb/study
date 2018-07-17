package spring.redis.listener;

import java.io.Serializable;

/**
 * @author tangxiaoshuang
 * @date 2018/7/13 17:57
 * @desc
 */
public class TestListener {

    public void handleMessage(Serializable msg) {
        System.out.println(Thread.currentThread().getName());
        for (int i=0; i<10; i++) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
