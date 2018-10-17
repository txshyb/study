package javase.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangxiaoshuang
 * @date 2018/6/7 11:23
 */
public class AtomicIntegerTest implements Runnable {

    private AtomicInteger i = new AtomicInteger(0);

    public int getValue() {
        return i.get();
    };
    private void add() {
        i.addAndGet(2);
     //   i.addAndGet(1);
    //    i.addAndGet(1);
    }
    @Override
    public void run() {
        while(true) {
            add();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicTest = new AtomicIntegerTest();

        executorService.execute(atomicTest);
        while(true) {
            int val = atomicTest.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }

    //    System.out.println(AtomicIntegerTest.class.getClassLoader());
    }
}
