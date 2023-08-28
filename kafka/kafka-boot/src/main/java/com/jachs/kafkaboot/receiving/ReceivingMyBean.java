package com.jachs.kafkaboot.receiving;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jachs.kafkaboot.entity.People;

/**
 * @author zhanchaohan
 * 
 */
@Component
public class ReceivingMyBean {

    @KafkaListener(topics = "tOne")
    public void processMessage(String peo) throws JsonMappingException, JsonProcessingException {
        System.out.println ("receiv:"+ peo.toString () );
        
        ObjectMapper mapper=new ObjectMapper ();
        People p=mapper.readValue ( peo, People.class );
        
        System.out.println ( p.getName () );
    }
    
}
