package thread.excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author tangxiaoshuang
 * @date 2018/5/28 15:45
 */
public class Worker implements Runnable {

    private Task task;
    private Logger logger = LoggerFactory.getLogger(Worker.class);

    public Worker(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    @Override
    public void run() {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName()+"|{}",task);
    }
}
