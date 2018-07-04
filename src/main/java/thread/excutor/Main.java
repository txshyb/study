package thread.excutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author tangxiaoshuang
 * @date 2018/5/28 15:54
 *
 * 线程池使用
 *
 * Java通过Executors提供四种线程池，分别为：
 * newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 * newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 * newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 * newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 *
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
      //  Executors.newFixedThreadPool()
        Worker[] workers = new Worker[10];
        for(int i = 0; i < workers.length ;i++) {
            workers[i] = new Worker(new Task(i,"内容"+i));
        }


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(2),
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

    }
}
