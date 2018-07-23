package javase.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author tangxiaoshuang
 * @date 2018/6/7 10:21
 *
 * 测试后台线程
 * 当所有非后台线程结束时，程序直接终止，如果此时有后台线程，后台线程也直接结束
 * 即使后台线程里有finally也不会执行
 */
public class Test implements Runnable{


    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程结束");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Test());
        thread.setDaemon(true); //设置该线程为后台线程 ，有了这句话 System.out.println("子线程结束");不会被执行到，，程序直接结束了
        thread.start();
        System.out.println("主线程结束");
    }
}
