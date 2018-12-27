package com.chen.kafka.modules;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
/**
 * 消息消费者
 * @author chen
 * @date 2018-12-27 10:43:37
 */
@Component
public class Consumer {
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);
	
    /**
     *  同时监听两个 topic 的消息了，可同时监听多个topic
     * @param record
     * @throws Exception
     */
    @KafkaListener(topics = {"test1"})
    public void listen (ConsumerRecord<?, ?> record) throws Exception {
    	Optional<?> kafkaMessage = Optional.ofNullable(record.value());
    	if (kafkaMessage.isPresent()) {
    		Object message = kafkaMessage.get();
    		log.info("消费者开始消费message：" + message);
    	}
    }
}
