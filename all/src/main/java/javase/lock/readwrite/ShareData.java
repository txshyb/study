package javase.lock.readwrite;

import java.util.Random;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 10:08
 * @desc:
 */
public class ShareData {
    private char[] c;
    public static final ReaderWriterLock lock = new ReaderWriterLock();
    private Random random = new Random();

    public ShareData(int size) {
        this.c = new char[size];
        for (int i = 0; i < size; i++) {
            c[i] = '*';
        }
    }

    public void write(String str) throws InterruptedException {
        try {
            lock.writeLock();
            doWriter(str);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWriter(String str) throws InterruptedException {
        int index = random.nextInt(str.length());
        System.out.println(lock.write + "____________________________________________________________________");
        for (int i = 0; i < c.length; i++) {
            c[i] = str.charAt(index);
        }
        System.out.println(System.currentTimeMillis());
        Thread.sleep(10000);
        System.out.println(System.currentTimeMillis());
     //   System.out.println(lock.write + "后____________________________________________________________________");
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();

            return doRead();
        } finally {
            lock.readUnlock();
        }

    }

    private char[] doRead() throws InterruptedException {
        char[] chars = new char[c.length];
        for (int i = 0; i < c.length; i++) {
            chars[i] = c[i];
        }
        Thread.sleep(1000);
        return chars;
    }
}
