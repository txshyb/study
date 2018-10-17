package javase.concurrentPattern.readwritelock;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 10:29
 * @desc:
 */
public class WriteThread implements Runnable {
    private ShareData shareData;
    private String str;

    public WriteThread(ShareData shareData) {
        this.shareData = shareData;
    }

    public WriteThread(ShareData shareData, String str) {
        this.shareData = shareData;
        this.str = str;
    }

    @Override
    public void run() {
        try {
            while (true) {
                shareData.write(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
