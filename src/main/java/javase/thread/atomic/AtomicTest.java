package javase.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tangxiaoshuang
 * @date 2018/6/7 11:18
 */
public class AtomicTest implements Runnable {

    private int i = 0;
    public int getValue() {
        return i;
    };
    private void add() {
        i = i +2 ;
    }
    @Override
    public void run() {
        while(true) {
            add();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicTest atomicTest = new AtomicTest();

        executorService.execute(atomicTest);
        while(true) {
            int val = atomicTest.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
