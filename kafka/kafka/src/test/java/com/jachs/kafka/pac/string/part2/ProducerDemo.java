package com.jachs.kafka.pac.string.part2;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.jachs.kafka.Console;

/***
 * 
 * @author zhanchaohan
 *
 */
public class ProducerDemo {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, 0);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> helloProducer = null;
		try {
			helloProducer = new KafkaProducer<String, String>(properties);
			for (int i = 0; i < 100; i++) {
				String msg = "This is Message " + i;
				if (i < 20) {
					helloProducer.send(new ProducerRecord<String, String>("A", msg));
				} else if (i >= 20 && i > 60) {
					helloProducer.send(new ProducerRecord<String, String>("B", msg));
				} else if (i >= 60 && i > 80) {
					helloProducer.send(new ProducerRecord<String, String>("C", msg));
				} else {
					helloProducer.send(new ProducerRecord<String, String>("D", msg));
				}
				System.out.println("Sent:" + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			helloProducer.close();
		}

	}
}
