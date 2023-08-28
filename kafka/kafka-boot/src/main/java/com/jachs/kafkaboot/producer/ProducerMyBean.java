package com.jachs.kafkaboot.producer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jachs.kafkaboot.entity.People;

/***
 * 
 * @author zhanchaohan
 *
 */
@Component
public class ProducerMyBean {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    
    int count=1;
    
    @Scheduled(cron = "0/5 * * * * ?")
    public void send() {
        People peo=new People();
        peo.setAge ( count++ );
        peo.setName ( "jack"+count );
        peo.setBirthday ( new Date () );
        
        kafkaTemplate.send ( "tOne", peo );
    }
    
}
