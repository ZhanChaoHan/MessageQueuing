package com.jachs.kafka.clients;


import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import com.jachs.kafka.Console;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Consumer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
		
		Map<String, List<PartitionInfo>> maps = kafkaConsumer.listTopics();//获取全部topic
		for (String key : maps.keySet()) {
			System.out.println(key);
		}
	}
}
