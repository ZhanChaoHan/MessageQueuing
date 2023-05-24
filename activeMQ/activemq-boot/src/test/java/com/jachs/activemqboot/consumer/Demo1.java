package com.jachs.activemqboot.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author zhanchaohan
 * 
 */
public class Demo1 {
    @Autowired
    private JmsMessagingTemplate messagingTemplate;
    
    @Test
    public void t1() {
        
    }
    
}
