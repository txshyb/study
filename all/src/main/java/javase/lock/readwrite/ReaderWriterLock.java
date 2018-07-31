package javase.lock.readwrite;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 09:44
 * @desc: 读写分离锁
 */
public class ReaderWriterLock {

    public int write = 0; //正在读的线程数
    public int writeWait = 0;//等待的线程数

    public int read = 0;//正在读的线程数
    public int readWait = 0;//等待读的线程数

    private boolean perferWrite = true;

    public ReaderWriterLock() {
        this(true);
    }

    public ReaderWriterLock(boolean perferWrite) {
        this.perferWrite = perferWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        try {
      /*      if (write < 0 || writeWait < 0 || read < 0 || readWait < 0) {
                System.out.println(write + "  " + writeWait + " " + read + " " + readWait);
                int i = 1 / 0;
            }*/
            if (write > 0 || (writeWait > 0 && perferWrite)) {
                //    if (true) {
                //       System.out.println(Thread.currentThread().getName() + "**********************************************************************************");
                readWait++;
                this.wait(); //等待写完
            }
            read++;
        } finally {
            //     write--;
        }
    }

    public synchronized void readUnlock() {
      /*  if (write < 0 || writeWait < 0 || read < 0 || readWait < 0) {
            System.out.println(write + "  " + writeWait + " " + read + " " + readWait);
            int i = 1 / 0;
        }*/
        read--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
    /*    if (write < 0 || writeWait < 0 || read < 0 || readWait < 0) {
            System.out.println(write + "  " + writeWait + " " + read + " " + readWait);
            int i = 1 / 0;
        }*/
        try {
            if (read > 0 || write > 0) {
                writeWait++;
                this.wait();
            }

        } finally {
            write = 10;
        }
    }

    public synchronized void writeUnlock() {
    /*    if (write < 0 || writeWait < 0 || read < 0 || readWait < 0) {
            System.out.println(write + "  " + writeWait + " " + read + " " + readWait);
            int i = 1 / 0;
        }*/
        write--;
        this.notifyAll();
    }

}
