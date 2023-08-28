package com.jachs.kafkaboot.send;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author zhanchaohan
 * 
 */
@SpringBootTest
public class T1 {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Test
    public void t1() {
        kafkaTemplate.send ( "someTopic", "helloworld" );
    }
}
