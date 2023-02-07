package com.jachs.kafka;

import java.util.Collections;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

public class SimpleDemo {
	private static final String topic = "system-metric-double";
	//private static final String clientIp= "127.0.0.1:9092,192.168.23.77:9092";
	private static final String clientIp= "192.168.2.150:9092";
	//生产者
	@Test
	public void Producer() throws InterruptedException {
		Properties p = new Properties();
		p.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, clientIp);// kafka地址，多个地址用逗号分割
		p.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		p.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(p);

		try {
			while (true) {
				String msg = "Hello," + new Random().nextInt(100);
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, msg);
				kafkaProducer.send(record);
				System.out.println("消息发送成功:" + msg);
				Thread.sleep(500);
			}
		} finally {
			kafkaProducer.close();
		}
	}
	//消费者
	@Test
	public void Consumer() {
		Properties p = new Properties();
		p.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, clientIp);
		p.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		p.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		p.put(ConsumerConfig.GROUP_ID_CONFIG, "duanjt_test");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
		kafkaConsumer.subscribe(Collections.singletonList(topic));// 订阅消息

		while (true) {
			ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println(String.format("topic:%s,offset:%d,消息:%s",record.topic(), record.offset(), record.value()));
			}
		}
	}
	
}
