package com.chen.jpa.service;

import java.util.List;

import com.chen.jpa.entity.MemberEntity;

/**
 * Service - 用户
 * @author chen
 * @date 2019-01-04 05:01:08
 */
public interface MemberService extends BaseService<MemberEntity, Long>{
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	MemberEntity findByUsername(String userName);

}