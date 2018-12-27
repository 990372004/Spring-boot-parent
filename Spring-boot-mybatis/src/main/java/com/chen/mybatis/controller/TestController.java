package com.chen.mybatis.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chen.mybatis.entity.CustomerEntity;
import com.chen.mybatis.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 测试
 * 
 * @author chen
 * @date 2018-12-27 02:58:22
 */
@RestController("testMybatisController")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);
	@Resource
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public PageInfo<CustomerEntity> ces(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNum", required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", required = true, defaultValue = "1") Integer pageSize) {
		log.info("返回json");
		PageHelper.startPage(pageNum, pageSize);//分页查询
		List<CustomerEntity> list  = customerService.selectByList(null, null, null);
		PageInfo<CustomerEntity> page = new PageInfo<CustomerEntity>(list);
		return page;
	}

}