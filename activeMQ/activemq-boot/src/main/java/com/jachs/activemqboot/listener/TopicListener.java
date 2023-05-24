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
public class TopicListener {
 
    /**
     * topic 模式/广播模式/发布订阅模式 一对多，多个消费者可同时接收到消息
     * topic 模式无死信队列，死信队列是queue模式
     * containerFactory属性的值关联config类中的声明
     *
     * @param msg
     */
    @JmsListener(destination = "active.topic", containerFactory = "jmsListenerContainerTopic")
    public void topicListener(ActiveMQMessage message, Session session, String msg) throws JMSException {
        try {
            // System.out.println("接收到消息：" + DateUtil.getStringDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println("active topic 接收到消息 " + msg);
            System.out.println("");
            //手动签收
            message.acknowledge();
        } catch (Exception e) {
            //重新发送
            session.recover();
        }
    }
 
    @JmsListener(destination = "active.topic", containerFactory = "jmsListenerContainerTopic")
    public void topicListener2(ActiveMQMessage message, Session session, String msg) throws JMSException {
        try {
            // System.out.println("接收到消息：" + DateUtil.getStringDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println("active topic2 接收到消息 " + msg);
            System.out.println("");
            //手动签收
            message.acknowledge();
        } catch (Exception e) {
            //重新发送
            session.recover();
        }
    }
}
