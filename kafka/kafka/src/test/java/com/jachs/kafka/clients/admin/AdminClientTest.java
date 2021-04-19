package com.jachs.kafka.clients.admin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DeleteTopicsOptions;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.junit.Before;
import org.junit.Test;

import com.jachs.kafka.Console;

/***
 * 
 * @author zhanchaohan
 * @see org.apache.kafka.clients.admin.AdminClient
 * @see org.apache.kafka.clients.AdminClientConfig
 * 
 * 源码包kafka-client下  common.message包下有全量json
 */
public class AdminClientTest {
	AdminClient ac;
	
	/***
	 * 初始化AdminClient對象
	 */
	@Before
	public void init() {
//		Properties properties=new Properties();//读取配置文件方式
//		ac=AdminClient.create(properties);
		
		//代码Map加载配置方式
		Map<String, Object> conf=new HashMap<String, Object>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);//服务地址
		
		ac=AdminClient.create(conf);
	}
	//打印全部的Topics
	@Test
	public void listTopics() throws InterruptedException, ExecutionException {
		ListTopicsResult listTopicsResult=ac.listTopics();
		KafkaFuture<Set<String>>sets=listTopicsResult.names();
		
		for (String name : sets.get()) {
			System.out.println(name);
		}
	}
	/***
	 * 删除Topics,需要配置server.properties配置文件的delete.topic.enable=true
	 */
	@Test
	public void deleteTopics() {
		Set<String>topics=new HashSet<String>();
		topics.add("topic1");
		topics.add("acs");
		topics.add("HelloWorld");
		
//		DeleteTopicsResult deleteTopicsResult=ac.deleteTopics(topics);
		
		//如果配额冲突应自动重试，则设置为true。
//		DeleteTopicsResult deleteTopicsResult=ac.deleteTopics(topics, new DeleteTopicsOptions().retryOnQuotaViolation(true));
		//保留此方法以保持与0.11的二进制兼容性
		DeleteTopicsResult deleteTopicsResult=ac.deleteTopics(topics, new DeleteTopicsOptions().timeoutMs(3000));
		
		
		for (String key : deleteTopicsResult.values().keySet()) {
			System.out.println(key);
			
			System.out.println("isCancelled:\t"+deleteTopicsResult.values().get(key).isCancelled());
			System.out.println("isCompletedExceptionally:\t"+deleteTopicsResult.values().get(key).isCompletedExceptionally());
			System.out.println("isDone:\t"+deleteTopicsResult.values().get(key).isDone());
			
			System.out.println("--------------------------------------------------------------");
		}
	}
}
