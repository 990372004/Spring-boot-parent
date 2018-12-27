package com.chen.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.chen.kafka.modules.Producer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.chen.kafka" })
@EnableAutoConfiguration
@EnableScheduling
public class SpringBootKafkaApplication {
	@Autowired
	private Producer<String> kafkaSender;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaApplication.class, args);
	}

	// 然后每隔2秒执行一次
	@Scheduled(fixedRate = 2000)
	public void testKafka() throws Exception {
		kafkaSender.send("消息发送者开始发送>>>");
	}
}
