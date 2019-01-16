package com.chen.jpa.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.chen.jpa.dao.MemberDao;
import com.chen.jpa.entity.MemberEntity;

/**
 * Dao - 会员信息
 * @author chen
 * @date 2019-01-04 05:03:14
 */
@Repository("memberJpaDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<MemberEntity, Long> implements MemberDao {

    @Override
    public MemberEntity find(Long id) {
        if (id == null) {
            return null;
        }
        try {
            String jpql = "select * from MemberEntity members where members.id = :id";
            return entityManager.createQuery(jpql, MemberEntity.class).setFlushMode(FlushModeType.COMMIT)
                    .setParameter("pers", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public MemberEntity findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        try {
            String jpql = "select members from MemberEntity members where lower(members.userName) = lower(:username)";
            return entityManager.createQuery(jpql, MemberEntity.class).setFlushMode(FlushModeType.COMMIT)
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
