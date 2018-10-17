package rocketmq.noorder;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.google.gson.Gson;
import entity.Person;

public class TestProducer {

    public static void main(String[] args)  {
        Gson gson = new Gson();
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("ptxs1");
        defaultMQProducer.setNamesrvAddr("172.171.51.151:9876");
        try {
            defaultMQProducer.start();

            for(int i = 0; i < 4; i ++) {
                Person p = new Person("a"+i,20+i);
                String msg = gson.toJson(p);
                Message message = new Message("txst","TAG1",msg.getBytes());
          //      message.setDelayTimeLevel(4);
                SendResult sendResult = defaultMQProducer.send(message);

                System.out.println(sendResult);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

     //   defaultMQProducer.shutdown();
    }
}
