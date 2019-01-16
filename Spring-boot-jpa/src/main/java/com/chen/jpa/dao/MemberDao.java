package com.chen.jpa.dao;

import com.chen.jpa.entity.MemberEntity;

public interface MemberDao extends BaseDao<MemberEntity, Long> {
	MemberEntity find(Long id);

	/**
	 * 根据用户名查找会员（忽略大小写）
	 * 
	 * @param username
	 *            用户名
	 * @return 会员，若不存在则返回null
	 */
	MemberEntity findByUsername(String username);
}
