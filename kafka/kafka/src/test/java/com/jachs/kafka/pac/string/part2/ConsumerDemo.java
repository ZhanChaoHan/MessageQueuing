package com.jachs.kafka.pac.string.part2;

import java.time.Duration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
		properties.put("bootstrap.servers", Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put("group.id", "group-1");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
		
		Set<String>topicSet=new HashSet<String>();
		topicSet.add("A");
		kafkaConsumer.subscribe(topicSet);
//		ConsumerRecords<String, String> pollMap=kafkaConsumer.poll(Duration.ofHours(1) );//一小时内数据
		ConsumerRecords<String, String> pollMap=kafkaConsumer.poll(Duration.ofMinutes(1) );//一分钟内数据
		
		for (ConsumerRecord<String, String> consumerRecord : pollMap) {
			System.out.println(consumerRecord.toString());
		}
		
	}

}
