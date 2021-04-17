package com.jachs.kafka.clients.string.consumer;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

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

		while (true) {
			Map<String, List<PartitionInfo>> maps = kafkaConsumer.listTopics();
			System.out.println("监听topics=" + maps.keySet());
			Set<String> sets = new HashSet<String>();
			for (String topic : maps.keySet()) {
				if (topic.startsWith("Hello")) { // 制定规则，监听哪一些的topic
					sets.add(topic);
				}
			}
			kafkaConsumer.subscribe(sets);
			long startTime = System.currentTimeMillis();
			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("offset = %d, value = %s, topic = %s", record.offset(), record.value(),
							record.topic());
					System.out.println("=====================>");
				}
				long endTime = System.currentTimeMillis();
				if (endTime - startTime > 5000) {
					System.out.println("------------------------------------------------------------------");
					break;
				}
			}
		}
	}

}
