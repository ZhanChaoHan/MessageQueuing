package com.jachs.kafkaboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KafkaBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBootApplication.class, args);
	}

}

