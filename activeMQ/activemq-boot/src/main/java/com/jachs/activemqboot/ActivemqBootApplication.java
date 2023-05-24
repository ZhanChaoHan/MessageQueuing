package com.jachs.activemqboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/***
    QUEUE 点对点：
    特点：消息遵循先到先得，消息只能被一个消费者消费。
    消息存储规则：消费者消费消息成功，MQ服务端消息删除
    TOPIC订阅模式: 消息属于广播（订阅）模式，消息会被所有的topic消费者消费消息。
    消息存储规则：所有消费者消费成功，MQ服务端消息删除，有一个消息没有没有消费完成，消息也会存储在MQ服务端。
    举例：
    已经处于运行topic消费者5个，5个消费者消费完成后，MQ服务端消息删除。
    扩展点补充：如果想额外添加topic消费者，如果MQ服务端消息没有被消费完毕，新增topic消费者可以消费以前未被消费的消息，
    正常新增的只会消费新的topic消息。
 *
 */
@EnableJms
@SpringBootApplication
public class ActivemqBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivemqBootApplication.class, args);
	}

}
