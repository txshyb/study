package rocketmq.transaction;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author tangxiaoshuang
 * @date 2018/6/15 9:57
 *
 *
 * 发送prepare消息，该消息对Consumer不可见
 * 执行本地事务
 * 若本地事务执行成功，则向MQ提交消息确认发送指令；若本地事务执行失败，则向MQ发送取消指令
 * 若MQ长时间未收到确认发送或取消发送的指令，则向业务系统询问本地事务状态，并做补偿处理
 *
 *
 * 事务消息占时不支持    以下注释仅是闭源版本的逻辑
 *
 *
 * 注释以转账为例
 */
public class ApacheProducer {

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
