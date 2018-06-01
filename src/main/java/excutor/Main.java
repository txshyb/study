package excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author tangxiaoshuang
 * @date 2018/5/28 15:54
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
      //  Executors.newFixedThreadPool()
        Worker[] workers = new Worker[10];
        for(int i = 0; i < workers.length ;i++) {
            workers[i] = new Worker(new Task(i,"内容"+i));
        }
/*
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new RejectedExecutionHandler() {   //拒绝策略
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        Worker worker = (Worker)r;
                        logger.info(worker.getTask()+"加入队列失败");
                    }
                }
        );
        for (int i = 0; i < workers.length; i++) {
            threadPoolExecutor.execute(workers[i]);
        }
*/

    }
}
