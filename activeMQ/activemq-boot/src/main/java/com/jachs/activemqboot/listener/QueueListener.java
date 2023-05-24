package com.jachs.activemqboot.listener;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Session;

/**
 * @author zhanchaohan
 * 
 */
@Component
public class QueueListener {
 
    /**
     * queue 模式 单对单，两个消费者监听同一个队列则通过轮询接收消息
     * containerFactory属性的值关联config类中的声明
     *
     * @param msg
     */
    @JmsListener(destination = "active.queue", containerFactory = "jmsListenerContainerQueue")
    public void queueListener(ActiveMQMessage message, Session session, String msg) throws JMSException {
        try {
            System.out.println("active queue 接收到消息 " + msg);
            //手动签收
            message.acknowledge();
        } catch (Exception e) {
            //重新发送
            session.recover();
        }
    }
}
