package designPatterns.volatile_;

import java.util.concurrent.TimeUnit;

public class Test2 {

    private volatile static int i = 0;
    private static int max = 10;
    public static void main(String[] args) {
        /*
        *因为该线程未改变i的值，所以cpu在优化代码时，只会从其缓存中拿i值，并不会去刷新主内存中的值，也不会去更改
        * 可以申明i为volatile，则会去主存中来，
        * 多线程买票的那个例子是因为每个线程都会更改i的值，所以会更新本线程缓存的值
        * */
        new Thread(new Runnable() {  //
            @Override
            public void run() {
                int local = i ;
                while(local<max) {
                    if(local != i) {
                        System.out.println(Thread.currentThread().getName() + " local :" + local);
                        local = i;
                    }
                }
            }
        },"T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int local = i ;
                while(local<max) {
                    System.out.println(Thread.currentThread().getName() + " local :" + local ++);
                    i = local;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"T2").start();
    }
}
