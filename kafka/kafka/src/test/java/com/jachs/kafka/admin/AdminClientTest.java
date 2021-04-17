package com.jachs.kafka.admin;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.junit.Before;
import org.junit.Test;

/***
 * 
 * @author zhanchaohan
 * @see org.apache.kafka.clients.admin.AdminClient
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
				
		ac=AdminClient.create(conf);
	}
	@Test
	public void test() {
		
	}
}
