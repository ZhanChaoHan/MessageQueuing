package com.jachs.activemqboot.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @author zhanchaohan
 * 
 */
@Component
public class MessageListener {
    @JmsListener(destination = "order.queue.id")
    @SendTo("order.other.queue.id")
    public String receive(String id){
        System.out.println("Listener事件监听，id："+id);
        return "new:"+id;
    }
}
