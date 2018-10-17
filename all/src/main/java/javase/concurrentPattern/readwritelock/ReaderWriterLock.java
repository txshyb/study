package javase.concurrentPattern.readwritelock;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/7/31 09:44
 * @desc: 读写分离锁
 */
public class ReaderWriterLock {

    public int writing = 0; //正在读的线程数
    public int writeWait = 0;//等待的线程数

    public int reading = 0;//正在读的线程数

    private boolean perferWrite = true;

    public ReaderWriterLock() {
        this(true);
    }

    public ReaderWriterLock(boolean perferWrite) {
        this.perferWrite = perferWrite;
    }

    public synchronized void readLock() throws InterruptedException {
        try {
            while (writing > 0 || (writeWait > 0 && perferWrite)) {
                this.wait(); //等待写完
            }
            reading++;
        } finally {
        }
    }

    public synchronized void readUnlock() {
        reading--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        writeWait++;
        try {
            while (reading > 0 || writing > 0) {

                this.wait();
            }

        } finally {
            writeWait--;
        }
        writing++;
    }

    public synchronized void writeUnlock() {
        writing--;
        this.notifyAll();
    }

}
