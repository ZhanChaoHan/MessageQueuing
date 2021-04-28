package com.jachs.kafka.pac.file.part1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
	private static String topic = "files";
	private static File fis=new File("e:\\f.xls");
	
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream(fis);
		ByteArrayOutputStream  baos=new ByteArrayOutputStream();
		byte []buffer=new byte[1024];
		
		while(is.read(buffer)!=-1) {
			baos.write(buffer, 0, buffer.length);
		}
		
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, 0);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
		
		
		Producer<String, byte[]> producer = null;
		try {
			producer = new KafkaProducer<String, byte[]>(properties);
			producer.send(new ProducerRecord<String, byte[]>(topic,fis.getName(), baos.toByteArray()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
			is.close();
		}

	}
}
