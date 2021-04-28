package com.jachs.kafka.pac.string.part2;

import java.time.Duration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.jachs.kafka.Console;

/***
 * @author zhanchaohan
 */
public class ConsumerDemo {
	
	public static void main(String[] args) throws InterruptedException {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
		
		Set<String>topicSet=new HashSet<String>();
		topicSet.add("A");
		kafkaConsumer.subscribe(topicSet);
//		ConsumerRecords<String, String> pollMap=kafkaConsumer.poll(Duration.ofHours(1) );//超时时间小时单位
//		ConsumerRecords<String, String> pollMap=kafkaConsumer.poll(Duration.ofMinutes(1) );//超时时间分钟单位
		
		ConsumerRecords<String, String> pollMap=kafkaConsumer.poll(50000 );//超时时间毫秒单位
		for (ConsumerRecord<String, String> consumerRecord : pollMap) {
			System.out.println(consumerRecord.toString());
		}
		
	}

}
