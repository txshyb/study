package rocketmq.transaction;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.*;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * @author tangxiaoshuang
 * @date 2018/6/15 9:57
 *
 * 事务消息占时不支持    以下注释仅是闭源版本的逻辑
 *
 *
 * 注释以转账为例
 */
public class TransactionProducer {

    public static void main(String[] args) throws MQClientException {

        TransactionMQProducer producer = new TransactionMQProducer("transactionProducer");
        producer.setNamesrvAddr("172.171.51.151:9876");
        producer.setTransactionCheckListener(new TransactionCheckListener() {
            @Override
            public LocalTransactionState checkLocalTransactionState(MessageExt msg) {   //以下事务消息不成功（即不可见）  则回调该方法，可做重新扣款操作 成功则返回COMMIT_MESSAGE     目前版本不支持回调该方法
                System.out.println("Listener msg : " + msg);
                return LocalTransactionState.UNKNOW;
            }
        });
        producer.start();


            Message message = new Message("transactionTopic","transactionTag","transaction".getBytes());
            TransactionSendResult result = producer.sendMessageInTransaction(message, new LocalTransactionExecuter() {

                //发送消息时的一个回调,添加业务代码，如扣除转账人金额   成功则返回COMMIT_MESSAGE  则消息对消费端可见    消费端无需特殊处理，只需要保证消费消息成功即可（消费失败概率很低，如果出现，则消费端自己想办法）
                @Override
                public LocalTransactionState executeLocalTransactionBranch(Message msg, Object arg) {
                    System.out.println("arg : " + arg);
                    System.out.println("Branch msg : " + msg);
                    return LocalTransactionState.UNKNOW;  //如果返回COMMIT_MESSAGE，则此消息在在mq里是对消费端可见的，如果返回是另外两个状态则保持消息对消费端不可见，回调上面的监听方法
                }
            },"hhhh");
            System.out.println(result);
    }
}
