package com.jachs.activemqboot;

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
        messagingTemplate.convertAndSend("order.queue.id","001");
    }
    
    @Test
    public void t2() {
        String id = messagingTemplate.receiveAndConvert("order.queue.id",String.class);
        
        System.out.println (id);
    }
}
