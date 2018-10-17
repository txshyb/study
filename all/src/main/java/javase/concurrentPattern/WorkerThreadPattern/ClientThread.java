package javase.concurrentPattern.WorkerThreadPattern;

import java.util.Random;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 11:19
 * @desc:
 */
public class ClientThread extends Thread {
    private final ThreadPoolChannel threadPoolChannel;
    private static final Random random = new Random();

    public ClientThread(String name,ThreadPoolChannel threadPoolChannel) {
        super(name);
        this.threadPoolChannel = threadPoolChannel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Work work = new Work(getName() ,i);
                threadPoolChannel.putWork(work);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
