package rocketmq.transaction;

import java.io.IOException;
import java.util.Date;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.LocalTransactionExecuter;
import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;


/**
 * @author: tangxiaoshuang
 * @date: 2018/10/16 18:13
 * @desc:
 */
public class Test {
    public static void main(String[] args) {

        TransactionMQProducer producer = new TransactionMQProducer();

        // 为了便于调试，设置超时时间放大1000倍。
        producer.setSendMsgTimeout(3000 * 1000);
        producer.setTransactionCheckListener(new TransactionCheckListener() {

            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
                System.out.println("--checkLocalTransactionState--" + (new Date()).toString());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.setNamesrvAddr("172.171.51.151:9876");
        producer.setProducerGroup("test_group");

        Message msg = new Message();
        msg.setTopic("test_topic");
        msg.setTags("test_tag");
        //     final BusinessServiceMock businessServiceMock = new BusinessServiceMock();
        try {
            producer.start();
            System.out.println("start...");
            int i = 10;
            while (true) {
                if (i == 10) {
                    for (int j = 0; j < 1; j++) {
                        msg.setBody(("hello word" + "-" + j + "-" + (new Date())).getBytes());
                        producer.sendMessageInTransaction(msg, new LocalTransactionExecuter() {

                            @Override
                            public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
//                                try
//                                {
//                                    System.out.println("--executeLocalTransactionBranch--" + (new Date()).toString());
//                                    businessServiceMock.randomExceptiponService();
//                                }
//                                catch (IllegalStateException e)
//                                {
//                                    e.printStackTrace();
//                                    return LocalTransactionState.ROLLBACK_MESSAGE;
//                                }
//                                catch (IllegalArgumentException e)
//                                {
//                                    e.printStackTrace();
//                                    return LocalTransactionState.UNKNOW; // 返回UNKNOW状态，在3.2.6版本中，是不支持此状态下回调TransactionCheckListener的
//                                }
//                                return LocalTransactionState.COMMIT_MESSAGE;
                                return LocalTransactionState.UNKNOW;
                            }
                        }, null);
                    }
                    System.out.println("end...");
                }
                i = System.in.read();
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        // catch (MQBrokerException e)
        // {
        // e.printStackTrace();
        // }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
