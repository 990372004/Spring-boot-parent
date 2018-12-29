package com.chen.redis.dao.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.chen.redis.dao.RedisDao;

@Repository(value = "redisDaoImpl")
public class RedisDaoImpl implements RedisDao{
	private static final Logger log = LoggerFactory.getLogger(RedisDaoImpl.class);
	static final String ngHisByte = "CHEN_REDIS";
	// @Autowired
	// private RedisCache redisCache;
	@Resource
	protected RedisTemplate<Object, Object> redisTemplate;
//	@Resource
//	protected StringRedisTemplate stringRedisTemplate;

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@Override
	public Boolean set(final String key, Object value) {
		log.info("根据key进行缓存>>>key = " + key);
		boolean result = false;
		try {
			ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Boolean set(final String key, Object value, Long time, TimeUnit timeUnit) {
		Boolean result = false;
		try {
			log.info("根据key进行缓存,并设置缓存时间>>>key = " + key);
//			ValueOperations<Serializable, Serializable> operations = redisTemplate.opsForValue();
//			operations.set(key,  SerializeUtil.serialize(value));
			ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
			operations.set(key,  value);
			if (null != time) {
				result = this.expire(key, time, timeUnit);
			}
		} catch (Exception e) {
			log.error("根据key进行缓存>>>key = " + key + ">>>>失败>>>>");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Object get(final String key) {
		log.info("获得缓存的基本对象>>>key=" + key);
		try {
			ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
//			return SerializeUtil.unserialize((byte[])operations.get(key));
			return operations.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}

	@Override
	public Boolean expire(String key, Long time, TimeUnit timeUnit) {
		log.info("设置缓存过期时间>>>");
		return redisTemplate.expire(key, time, timeUnit);
	}

	@Override
	public void removePattern(String pattern) {
		log.info("批量删除key");
		Set<Object> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);

	}

	@Override
	public void remove(String key) {
		log.info("删除对应的value>>>key = " + key);
		if (hasKey(key)) {
			redisTemplate.delete(key);
		}
	}

	@Override
	public Boolean hasKey(String key) {
		log.info("判断缓存中是否有对应的value>>>");
		return redisTemplate.hasKey(key);
	}

}
