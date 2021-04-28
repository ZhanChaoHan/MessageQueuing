package com.jachs.kafka.pac.file.part1;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.jachs.kafka.Console;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ConsumerDemo {
	private static String filepath = "e:\\log\\";

	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put("group.id", "group-1");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

		KafkaConsumer<String, byte[]> kafkaConsumer = new KafkaConsumer<String, byte[]>(properties);

		Set<String> topics = new HashSet<String>();
		topics.add("files");
		kafkaConsumer.subscribe(topics);
		ConsumerRecords<String, byte[]> records = kafkaConsumer.poll(50000);//等50秒
		for (ConsumerRecord<String, byte[]> record : records) {
			OutputStream os = new FileOutputStream(filepath+record.key());

			os.write(record.value());
			os.close();
		}
	}

}
