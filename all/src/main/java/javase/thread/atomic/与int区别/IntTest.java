package javase.thread.atomic.与int区别;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/2 10:59
 * @desc:
 */
public class IntTest implements Runnable{

    private int atomicInteger = 0;

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            atomicInteger--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntTest atomicTest = new IntTest();
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(atomicTest);
        }
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println(atomicTest.atomicInteger);
    }
}
