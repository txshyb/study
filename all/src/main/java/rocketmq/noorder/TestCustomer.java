package rocketmq.noorder;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.google.gson.Gson;
import entity.Person;

import java.util.List;

public class TestCustomer {

    public static void main(String[] args) {
        final Gson gson = new Gson();
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer ("ctxs1");
        //向中心注册消费者，相当于运行一次main，就注册一个消费者
        defaultMQPushConsumer.setNamesrvAddr("172.171.51.151:9876");
        try {
         //   defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //BROADCASTING  广播消费    注册的所有消费者都会消费所有消息，一条消息被所有Consumer消费
            //CLUSTERING  集群消费       注册的消费者平分所有消息   一条消息只被该Group中一个Consumer消费
         //   defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);

            // 程序第一次启动从消息队列头取数据
            // 如果非第一次启动，那么按照上次消费的位置继续消费
            defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
            //一次推送10条消息   则下面的List<MessageExt> list  size为10
            defaultMQPushConsumer.setConsumeMessageBatchMaxSize(2);

            defaultMQPushConsumer.subscribe("txst","*");
            defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                int i=0;
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    System.out.println(list.size());
                    for (MessageExt msg: list) {
                 //       i++;
                        System.out.println(Thread.currentThread().getName()+":"+gson.fromJson(new String(msg.getBody()), Person.class));
                    }
                //    System.out.println(i + "  :::::::::::::");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            defaultMQPushConsumer.start();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
