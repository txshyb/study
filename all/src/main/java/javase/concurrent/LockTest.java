package javase.concurrent;

import javax.xml.soap.Node;
import java.util.concurrent.locks.ReentrantLock;
import  java.util.concurrent.locks.AbstractOwnableSynchronizer.*;

public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    lock.lock();
                    try {
                        Thread.sleep(10000);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            thread.start();
        }
        System.out.printf("ererfd");
    }

}
