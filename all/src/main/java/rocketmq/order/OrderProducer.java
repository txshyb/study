package rocketmq.order;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.google.gson.Gson;
import entity.Person;

import java.util.List;

public class OrderProducer {

    public static void main(String[] args) {
        Gson gson = new Gson();
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("producer2");
        defaultMQProducer.setNamesrvAddr("172.171.51.151:9876");
        try{
            defaultMQProducer.start();

            for(int i=0;i<4;i++){
                Person p = new Person("p"+i,20+i);
                int orderId = i % 4; //具有相同orderId的数据是有序消费的，它们都会被投递到同一个queue
                Message msg = new Message("b", "Tagtest", gson.toJson(p).getBytes());

                SendResult sendResult = defaultMQProducer.send(msg, new MessageQueueSelector() {
                    // 选择发送消息的队列   会把orderId相同的消息放到一个queue中，一个queue对应一个线程，取的时候用一个线程从一个queue中取消息
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        //以下是实现顺序的代码，发送的消息会放到返回的MessageQueue中，放到相同MessageQueue中的消息消费的时候也是顺序的
                        //比如改为return mqs.get(0);  则永远是顺序的
                        // arg的值就是orderId
                        Integer id = (Integer) arg;
                        // mqs是队列集合，也就是topic所对应的所有队列
                        int index = id % mqs.size();
                        System.out.println("id="+id+",index="+index+",mqs="+mqs.size());
                        // 这里根据前面的id对队列集合大小求余来返回所对应的队列
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.println(sendResult);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            defaultMQProducer.shutdown();
        }
    }
}
