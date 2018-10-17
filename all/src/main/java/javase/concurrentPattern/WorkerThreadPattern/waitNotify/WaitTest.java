package javase.concurrentPattern.WorkerThreadPattern.waitNotify;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 14:36
 * @desc:
 */
public class WaitTest {

    private static int i;

    /**
     * 虽然changeI是同步的，但是因为其wait   等被唤醒   i值已经被其他线程更改
     * synchronized 只保证某一时刻只有一个线程操作该代码块
     * @throws InterruptedException
     */
    public synchronized void changeI() throws InterruptedException {
        i ++;
        if(true) {
            this.wait();
        }
        System.out.println(i);
    }
    public synchronized void notifyALL() {
        this.notifyAll();
    }
}
