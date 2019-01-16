package com.chen.jpa.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chen.jpa.dao.MemberDao;
import com.chen.jpa.entity.MemberEntity;
import com.chen.jpa.repository.MemberRepository;
import com.chen.jpa.service.MemberService;

/**
 * Service - 会员
 * @author chen
 * @date 2019-01-04 05:06:42
 */
@Service("memberJpaServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<MemberEntity, Long> implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
    private MemberDao memberDao;
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public MemberEntity findByUsername(String userName) {
		log.info("根据用户名查询信息>>>");
		return memberDao.findByUsername(userName);
	}
	

}