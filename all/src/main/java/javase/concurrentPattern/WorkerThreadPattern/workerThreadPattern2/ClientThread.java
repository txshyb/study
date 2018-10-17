package javase.concurrentPattern.WorkerThreadPattern.workerThreadPattern2;

import java.util.Random;

/**
 * @auther: tangxiaoshuang
 * @date: 2018/8/1 15:15
 * @desc:
 */
public class ClientThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random();
    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                channel.putRequest(request);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
        }
    }
}
