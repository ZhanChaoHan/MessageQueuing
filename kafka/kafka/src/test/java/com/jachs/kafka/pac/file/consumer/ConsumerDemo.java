package com.jachs.kafka.pac.file.consumer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
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
	private static String filepath="e:\\dev";
	
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put("group.id", "group-1");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "earliest");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteBufferDeserializer");

		KafkaConsumer<String,ByteBuffer> kafkaConsumer = new KafkaConsumer<String,ByteBuffer>(properties);

		Set<String>topics=new HashSet<String>();
		topics.add("files");
		topics.add("f.xls");
		kafkaConsumer.subscribe(topics);
		while (true) {
			ConsumerRecords<String,ByteBuffer> records = kafkaConsumer.poll(100);
			for (ConsumerRecord<String,ByteBuffer> record : records) {
				System.out.println(record.key());
				OutputStream os=new FileOutputStream(filepath+File.separator+record.key());
				
				os.write(record.value().array());
				os.close();
			}
		}
	}

}
