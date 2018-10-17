package javase.concurrentPattern.WorkerThreadPattern;


/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 11:20
 * @desc:
 */
public class ThreadPoolChannel {

    private static final int MAX_REQUEST = 50;
    private final Work[] requestQueue;
    private int tail;
    private int head;
    private int count;
    private final WorkerThread[] threadPool;



    public ThreadPoolChannel(int size) {
        requestQueue = new Work[MAX_REQUEST];
        tail = 0;
        head = 0;
        count = 0;

        threadPool = new WorkerThread[size];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread("work-"+i,this);
        }
    }

    public void startThread() {
        for (WorkerThread workerThread : threadPool) {
            workerThread.start();
        }
    }

    public synchronized void putWork(Work work) throws InterruptedException {
        while (count >= requestQueue.length) {
            try {
                this.wait();
            }catch (Exception e) {

            }
        }
        requestQueue[tail] = work;

        tail = (tail + 1) % requestQueue.length;
        count++;
        this.notifyAll();
    }

    public synchronized Work takeWork() throws InterruptedException {
        while (count <= 0) {
            this.wait();
        }
        Work work = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        this.notifyAll();
        return work;
    }

}
