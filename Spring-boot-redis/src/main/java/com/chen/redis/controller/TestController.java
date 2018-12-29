package com.chen.redis.controller;

import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chen.redis.dao.RedisDao;

/**
 * 测试
 * 
 * @author chen
 * @date 2018-12-27 02:58:22
 */
@RestController("testRedisController")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	@Resource
	private RedisDao redisDao;

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> ces(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
		Map<String, Object> map = new TreeMap<>();
		log.info("返回json");
		redisDao.set("ces", "我的测试消息",Long.valueOf(5),TimeUnit.MINUTES);
		redisDao.set("wode", "sjakdsajfas", Long.valueOf(5),TimeUnit.SECONDS);
		log.info("保存在redis中的值为>>>"+redisDao.get("wode")+" 当前时间>>>"+LocalTime.now());
		map.put("time1", "保存在redis中的值为>>>"+redisDao.get("wode")+" 当前时间>>>"+LocalTime.now());
		Thread.sleep(5000);//暂停六秒在执行
		log.info("保存在redis中的值为>>>"+redisDao.get("wode")+LocalTime.now());
		map.put("time2", "保存在redis中的值为>>>"+redisDao.get("wode")+" 当前时间>>>"+LocalTime.now());
		return map;
	}

}