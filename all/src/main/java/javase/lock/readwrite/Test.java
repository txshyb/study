package javase.lock.readwrite;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 10:30
 * @desc:
 */
public class Test {
    public static void main(String[] args) {
        ShareData shareData = new ShareData(10);
        new Thread(new WriteThread(shareData, "12345667"),"writeThread").start();
        for (int i = 0; i < 10; i++) {
            new Thread(new ReadThread(shareData),"thread"+i ).start();

        }

    }
}
