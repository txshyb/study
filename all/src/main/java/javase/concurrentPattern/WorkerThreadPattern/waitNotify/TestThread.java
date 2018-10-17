package javase.concurrentPattern.WorkerThreadPattern.waitNotify;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 14:39
 * @desc:
 */
public class TestThread extends Thread{
    private WaitTest waitTest;

    public TestThread(WaitTest waitTest) {
        this.waitTest = waitTest;
    }

    @Override
    public void run() {
        try {
            waitTest.changeI();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
