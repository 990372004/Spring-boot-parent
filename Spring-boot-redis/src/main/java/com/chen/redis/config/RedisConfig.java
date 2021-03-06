package com.chen.redis.config;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.chen.redis.properties.RedisProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis 事物支持
 * 
 * @author chen
 * @date 2018-12-28 04:46:38
 */
@Configuration
@EnableTransactionManagement
public class RedisConfig {
	private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);
	// @Autowired
	// DataSource dataSource;
	// 自动注入redis配置属性文件
	@Autowired
	private RedisProperties properties;

	public JedisPoolConfig setJedisPoolConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(properties.getJedis().getPool().getMaxIdle());
		config.setMaxTotal(properties.getJedis().getPool().getMaxActive());
		config.setMaxWaitMillis(properties.getJedis().getPool().getMaxWait().toMillis());
		config.setTestOnBorrow(properties.getJedis().getPool().isTestOnBorrow());
		return config;
	}

	@Bean
	public JedisPool redisPoolFactory() {
		log.info("JedisPool注入成功！！");
		log.info("redis地址：" + properties.getHost() + ":" + properties.getPort());
		JedisPoolConfig config = this.setJedisPoolConfig();
		JedisPool pool = new JedisPool(config, properties.getHost(), properties.getPort(), properties.getTimeout(),
				properties.getPassword(), properties.getDatabase());
		return pool;
	}

	@SuppressWarnings("deprecation")
	@Bean
	@ConditionalOnMissingBean(name = "connectionFactory")
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(properties.getHost());
		factory.setTimeout(properties.getTimeout());
		factory.setPort(properties.getPort());
		factory.setDatabase(properties.getDatabase());
		factory.setPassword(properties.getPassword());
		factory.setPoolConfig(this.setJedisPoolConfig());
		return factory;
	}

	@Bean
	@ConditionalOnMissingBean(name = "redisTemplate")
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
		template.setConnectionFactory(redisConnectionFactory);
		// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
		Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		log.info("==============obj:+Object.class.getName()");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		serializer.setObjectMapper(mapper);
		template.setValueSerializer(serializer);
		// 使用StringRedisSerializer来序列化和反序列化redis的key值
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		template.setEnableTransactionSupport(true);
		return template;
	}

	@Bean
	@ConditionalOnMissingBean(name = "stringRedisTemplate")
	public StringRedisTemplate stringRedisTemplate() throws UnknownHostException {
		StringRedisTemplate template = new StringRedisTemplate();
		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
				Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		template.setConnectionFactory(this.connectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}
	// @Bean
	// @ConditionalOnMissingBean(name = "stringRedisTemplate")
	// public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory
	// redisConnectionFactory)
	// throws UnknownHostException {
	// StringRedisTemplate template = new StringRedisTemplate();
	// template.setConnectionFactory(redisConnectionFactory);
	// return template;
	// }

	// @Bean
	// public DataSource dataSource() {
	// return dataSource;
	// }

}
