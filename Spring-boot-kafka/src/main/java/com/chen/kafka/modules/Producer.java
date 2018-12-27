package com.chen.kafka.modules;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
/**
 * 消息生产者
 * @author chen
 * @date 2018-12-27 10:43:45
 * @param <T>
 */
@Component
public class Producer<T> {
	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaTemplate kafkaTemplate;

	// 发送消息方法
	@SuppressWarnings("unchecked")
	public void send(T t ) {
		log.info("发送消息方法");
		// 这个 topic 在 Java 程序中是不需要提前在 Kafka 中设置的，因为它会在发送的时候自动创建你设置的 topic
		kafkaTemplate.send("test1", t+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	}
}
