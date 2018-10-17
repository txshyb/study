package javase.concurrentPattern.WorkerThreadPattern.waitNotify;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 14:38
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        WaitTest waitTest = new WaitTest();
        for (int i=0;i<10;i++) {
            new TestThread(waitTest).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitTest.notifyALL();
    }
}
