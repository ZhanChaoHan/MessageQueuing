package com.jachs.kafka;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.Test;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Topic {
	private static final String clientIp= "192.168.2.150:9092";
	//system-metric-data-type  system-metric-tag  system-metric-double  transcript-topic
	String topic="system-metric-double";
	
	//获取全部topic
	@Test
	public void t3() throws InterruptedException, ExecutionException {
		Properties p = new Properties();
		p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, clientIp);
		ListTopicsResult result = KafkaAdminClient.create(p).listTopics();
		KafkaFuture<Set<String>> set = result.names();
			
		for (String key : set.get()) {
			System.out.println(key);
		}
	}
	//新建topic
	@Test
	public void t2() throws InterruptedException, ExecutionException {
		Properties p = new Properties();
		p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, clientIp);
		
		NewTopic newTopic = new NewTopic(topic, 3, (short) 1);
		CreateTopicsResult result = KafkaAdminClient.create(p).createTopics(Collections.singletonList(newTopic));
		result.all().get();
	}
	//删除topic
	@Test
	public void t1() {
		Properties p = new Properties();
		p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, clientIp);
		
		AdminClient adminClient = AdminClient.create(p);
	    DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Collections.singleton(topic));
	
	    Map<String, Boolean> map = new HashMap<>();
        try {
            for (Map.Entry<String, KafkaFuture<Void>> entry : deleteTopicsResult.values().entrySet()) {
                String topic = entry.getKey();
                KafkaFuture<Void> future = entry.getValue();
                future.get();//执行
                map.put(topic, !future.isCompletedExceptionally());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
