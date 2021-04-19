package com.jachs.kafka.pac.file.producer;


import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
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
	private static String fileName = "f.xls";
	private static ByteBuffer bos;

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("e:\\f.xls");
		byte[] bArr = new byte[1024];
		while (is.read(bArr) != -1) {
			bos.wrap(bArr, 0, bArr.length);
		}

		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put("retries", 0);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteBufferSerializer");
		
		
		
		Producer<String, ByteBuffer> producer = null;
		try {
			producer = new KafkaProducer<String, ByteBuffer>(properties);
			producer.send(new ProducerRecord<String, ByteBuffer>(fileName, bos));
			System.out.println(fileName);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			producer.close();
			is.close();
		}

	}
}
