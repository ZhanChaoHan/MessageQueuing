package com.jachs.kafka.clients.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.junit.Before;
import org.junit.Test;

import com.jachs.kafka.Console;

/***
 * 
 * @author zhanchaohan
 * @see org.apache.kafka.clients.admin.AdminClient
 * @see org.apache.kafka.clients.AdminClientConfig
 */
public class AdminClientTest {
	AdminClient ac;
	
	/***
	 * 初始化AdminClient對象
	 */
	@Before
	public void init() {
		Properties properties=new Properties();//读取配置文件方式
//		ac=AdminClient.create(properties);
		
		Map<String, Object> conf=new HashMap<String, Object>();
		conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, Console.BOOTSTRAP_SERVERS_CONFIG);//服务地址
		
		
		ac=AdminClient.create(conf);
	}
	@Test
	public void test() {
		
	}
}
