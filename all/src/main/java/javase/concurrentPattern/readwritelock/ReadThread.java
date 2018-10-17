package javase.concurrentPattern.readwritelock;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 10:14
 * @desc:
 */
public class ReadThread implements Runnable {

    private ShareData shareData;

    public ReadThread(ShareData shareData) {
        this.shareData = shareData;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char[] chars = shareData.read();
                System.out.println(Thread.currentThread().getName() + ":" + new String(chars));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
