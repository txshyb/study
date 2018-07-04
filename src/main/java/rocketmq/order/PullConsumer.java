package rocketmq.order;

import com.alibaba.rocketmq.client.consumer.DefaultMQPullConsumer;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author tangxiaoshuang
 * @date 2017/12/1 10:46
 *
 * pullConsumer 主动拉取消息，只有主动pull的时候才会拿到消息，需要自己记录每次拉取后的位置，方便下次从该处再拉取
 *
 */
public class PullConsumer {

    static DefaultMQPullConsumer consumer ;

        public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
            consumer = new DefaultMQPullConsumer("Group1");
            consumer.setNamesrvAddr("172.171.51.151:9876");
            consumer.start();
            Set<MessageQueue> mqs = consumer.fetchSubscribeMessageQueues("b");
            System.out.println("Queue :" + mqs.size());
            for (MessageQueue messageQueue : mqs) {
                pullMessage(messageQueue);
            }
         //   consumer.shutdown();
        }

    public static void pullMessage(MessageQueue mq) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        if (mq != null) {
            System.out.println("Consume from the queue: " + mq);
            // offset 为每次拉取的开始位置

            //获取该 MessageQueue队列下面从offset位置开始的消息内容，其中maxNums即表示获取的最大消息个数，offset为该MessageQueue对象的开始消费位置，
            // 可以调用DefaultMQPullConsumer.fetchConsumeOffset(MessageQueue mq, boolean fromStore)方法获取该MessageQueue队列的消费进度来设定参数offset值
            long offSet = consumer.fetchConsumeOffset(mq,false);
            System.out.println(offSet + "   ::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
            PullResult pullResult = consumer.pullBlockIfNotFound(mq, null, offSet, 100);
            List<MessageExt> msgs = pullResult.getMsgFoundList();
            if (CollectionUtils.isEmpty(msgs)) {
                return;
            }
            for (MessageExt m : msgs) {
                System.out.println(Thread.currentThread().getName() + ":" + new String(m.getBody()));
            }
        }
    }
}
