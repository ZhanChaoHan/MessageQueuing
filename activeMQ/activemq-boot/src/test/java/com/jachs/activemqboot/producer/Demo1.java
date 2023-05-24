package com.jachs.activemqboot.producer;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class Demo1 {
    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    
    
    @Test
    public void t1() {
        String msg="queue一条消息";
        
        ActiveMQQueue queue = new ActiveMQQueue("order.queue.id");
        messagingTemplate.convertAndSend(queue, msg);
    }
    
    @Test
    public void t2() {
        String msg="topic一条消息";
        
        ActiveMQTopic topic = new ActiveMQTopic("test");
        messagingTemplate.convertAndSend(topic, msg);
    }
}
