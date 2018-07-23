package rocketmq.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;


/**
 * pushConsmer 会消费掉信息，只要该线程存在，会一直消费消息
 */
public class OrderCustomer {
    public static void main(String[] args) throws Exception {
        //声明并初始化一个consumer
        //需要一个consumer group名字作为构造方法的参数，这里为concurrent_consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ordered_consumer");

        //同样也要设置NameServer地址
        consumer.setNamesrvAddr("172.171.51.151:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //设置consumer所订阅的Topic和Tag
        consumer.subscribe("b", "*");
        //每次拉取消费最多10条
        consumer.setConsumeMessageBatchMaxSize(10);
        //设置一个Listener，主要进行消息的逻辑处理
        //注意这里使用的是MessageListenerOrderly这个接口
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                for (MessageExt m : msgs) {
                   System.out.println(Thread.currentThread().getName()+":"+new String(m.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //调用start()方法启动consumer
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
