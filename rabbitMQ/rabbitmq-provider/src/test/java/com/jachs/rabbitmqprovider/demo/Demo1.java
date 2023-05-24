package com.jachs.rabbitmqprovider.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class Demo1 {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    
    @Test
    public void t1(){
      String routeKey = "hello";
      String context = "hello " + new Date();
      System.out.println("Sender:"+context);
      this.rabbitTemplate.convertAndSend(routeKey, context);
    }
    
    @Test
    public void t2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: M A N ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
    }
}
