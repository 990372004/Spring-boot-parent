package com.chen.mybatis.service;

import java.util.List;

import com.chen.mybatis.entity.CustomerEntity;
import com.chen.mybatis.entity.CustomerEntityExample;

/**
 * 测试
 * @author chen
 * @date 2018-12-27 02:43:54
 */
public interface CustomerService extends BaseService<CustomerEntity,CustomerEntityExample, Integer> {

	/**
	 * 根据条件获取集合
	 * @param cname
	 * @param username
	 * @param password
	 * @return
	 */
	List<CustomerEntity> selectByList(String cname, String username, String password);
}
