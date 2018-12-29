package com.chen.redis.dao;

import java.util.concurrent.TimeUnit;

public interface RedisDao {
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	Boolean set(final String key, Object value) ;
	/**
	 * 缓存基本的对象，Integer、String、实体类等
	 * @param key 缓存的键
	 * @param value  缓存的值
	 * @param time  时间
	 * @param timeUnit  时间单位 SECONDS-秒 MINUTES-分钟  HOURS-小时  DAYS-天
	 */
	Boolean set(final String key, Object value, Long time, TimeUnit timeUnit);
	/**
	 * 获得缓存的基本对象。
	 * @param key  缓存键值
	 * @return  缓存键值对应的数据
	 */
	Object get(final String key);
	/**
	 * 设置缓存过期时间
	 * @param key
	 * @param time  时间
	 * @param timeUnit 时间单位 SECONDS-秒 MINUTES-分钟  HOURS-小时  DAYS-天
	 * @return
	 */
	Boolean expire(String key, Long time, TimeUnit timeUnit);
	/**
	 * 批量删除key
	 * @param pattern
	 */
	void removePattern(final String pattern);
	/**
	 * 删除对应的value
	 * @param key
	 */
	void remove(final String key);
	/**
	 * 判断缓存中是否有对应的value
	 * @param key
	 * @return
	 */
	Boolean hasKey(final String key);
}
