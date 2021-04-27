package com.jachs.kafka.clients;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.GroupRebalanceConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;

/***
 * 
 * @author zhanchaohan
 *
 */
public class Configs {
	private CommonClientConfigs commonClientConfigs;
	private GroupRebalanceConfig groupRebalanceConfig;

	// 生产者全部配置
	private ProducerConfig producerConfig;
	// 消费者全部配置
	private ConsumerConfig consumerConfig;

}
