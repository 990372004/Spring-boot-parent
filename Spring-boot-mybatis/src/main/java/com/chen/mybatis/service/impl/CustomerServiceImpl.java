package com.chen.mybatis.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.mybatis.dao.CustomerDao;
import com.chen.mybatis.entity.CustomerEntity;
import com.chen.mybatis.entity.CustomerEntityExample;
import com.chen.mybatis.service.CustomerService;

@Service
public class CustomerServiceImpl extends BaseServiceImpl<CustomerEntity, CustomerEntityExample, Integer>
		implements CustomerService {
	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;

	@Override
	public List<CustomerEntity> selectByList(String cname, String username, String password) {
		log.info("多条件查询 >>>  相当于JPA中封装的查询一样，只是当前是通过generatorConfig.xml生成的查询语句，相当于自己封装的语句");
		List<CustomerEntity> list = null;
		try {
			CustomerEntityExample example = new CustomerEntityExample();
			example.setDistinct(true);// 是否去重
			example.setOrderByClause("cname desc");// 排序 cname为数据库中字段名 desc-排序规则
			// 加第一个条件查询器>>>也可以加入多个条件查询器，各查询器中可以是或的关系
			CustomerEntityExample.Criteria criteria = example.createCriteria();
			if (null != cname && "".equals(cname)) {
				criteria.andCnameEqualTo(cname);// 条件器中加入等于当前值
			}
			if (null != username && "".equals(username)) {
				criteria.andUsernameEqualTo(username);// 条件器中加入等于当前值
			}
			if (null != password && "".equals(password)) {
				criteria.andPasswordEqualTo(password);// 条件器中加入等于当前值
			}
			list = customerDao.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
