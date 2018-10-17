package javase.thread.atomic.与int区别;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/2 10:55
 * @desc: 在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口
 *  在高并发的情况下 AtomicInteger的增减是安全的，而i++、++i、--i、i-- 不是   并发情况下用Atomic是安全的
 *  此处的高并发要上万，  如果并发比较小，int是看不出来区别的
 */
public class AtomicTest implements Runnable {


    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            atomicInteger.addAndGet(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicTest atomicTest = new AtomicTest();

        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(atomicTest);
        }
        executorService.shutdown();

        Thread.sleep(1000);
        System.out.println(atomicTest.atomicInteger.get());
    }
}
