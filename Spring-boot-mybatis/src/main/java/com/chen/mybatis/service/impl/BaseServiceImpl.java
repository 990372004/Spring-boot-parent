package com.chen.mybatis.service.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chen.mybatis.dao.BaseDao;
import com.chen.mybatis.service.BaseService;

/**
 * Service - 基类
 * @author chen
 * @date 2018-12-27 02:40:13
 * @param <T>
 * @param <U>
 * @param <ID>
 */
@Service
public class BaseServiceImpl<T, U, ID extends Serializable> implements BaseService<T, U, ID> {
	private static final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);
	/** 基类DAO */
	@Autowired
	private BaseDao<T, U, ID> baseDao;

	@Override
	public long countByExample(U example) {
		log.info("查询总数量>>>");
		return baseDao.countByExample(example);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public int deleteByExample(U example) {
		log.info("根据条件删除信息>>>");
		return baseDao.deleteByExample(example);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public Integer deleteByPrimaryKey(ID id) {
		log.info("根据id删除>>");
		return baseDao.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public Integer insert(T record) {
		log.info("插入信息>>>");
		return baseDao.insert(record);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public Integer insertSelective(T record) {
		log.info("插入信息>>>");
		return baseDao.insertSelective(record);
	}

	@Override
	public List<T> selectByExample(U example) {
		log.info("根据条件查询集合>>>");
		return baseDao.selectByExample(example);
	}

	@Override
	public T selectByPrimaryKey(ID id) {
		log.info("根据id查询信息>>>");
		return baseDao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public int updateByExampleSelective(T record, U example) {
		log.info("根据条件更新  判空>>>");
		return baseDao.updateByExampleSelective(record, example);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public int updateByExample(T record, U example) {
		log.info("根据条件更新   不判空>>>");
		return baseDao.updateByExample(record, example);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public Integer updateByPrimaryKeySelective(T record) {
		log.info("根据id更新信息  判空>>>");
		return baseDao.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(rollbackForClassName={"RuntimeException","Exception"},rollbackFor = { RuntimeException.class, Exception.class }, propagation = Propagation.REQUIRED)
	public Integer updateByPrimaryKey(T record) {
		log.info("根据id更新信息  不判空>>>");
		return baseDao.updateByPrimaryKey(record);
	}

	@Override
	public void initMapper() {
	}

}
