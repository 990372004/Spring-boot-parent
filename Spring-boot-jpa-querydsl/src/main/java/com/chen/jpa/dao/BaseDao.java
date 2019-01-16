package com.chen.jpa.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.LockModeType;

import com.chen.jpa.Page;
import com.chen.jpa.Pageable;
import com.chen.jpa.Sequencer;
import com.chen.jpa.entity.BaseEntity;
import com.chen.jpa.filter.Filter;

/**
 * dao - 基类
 * 
 * @author chen
 * @date 2019-01-03 11:01:05
 * @param <T>
 * @param <ID>
 */
public interface BaseDao<T extends BaseEntity, ID extends Serializable> {// extends JpaRepository<T, ID>

	/**
	 * 查找实体
	 * 
	 * @param id
	 *            ID
	 * @return 实体
	 */
	T find(ID id);

	/**
	 * 查找实体
	 * 
	 * @param id
	 *            ID
	 * @param lockModeType
	 *            锁定方式
	 * @return 实体
	 */
	T find(ID id, LockModeType lockModeType);

	/**
	 * 查找实体集合
	 * 
	 * @param first
	 *            起始记录
	 * @param count
	 *            查询数量
	 * @param filters
	 *            过滤器
	 * @param sequencers
	 *            定序器
	 * @return 实体集合
	 */
	List<T> findList(Integer first, Integer count, List<Filter> filters, List<Sequencer> sequencers);

	/**
	 * 查找实体分页
	 * 
	 * @param pageable
	 *            分页信息
	 * @return 实体分页
	 */
	Page<T> findPage(Pageable pageable);

	/**
	 * 查询实体数量
	 * 
	 * @param filters
	 *            筛选
	 * @return 实体数量
	 */
	Long count(List<Filter> filters);

	/**
	 * 获取ID
	 * 
	 * @param entity
	 *            实体
	 * @return ID
	 */
	ID getIdentifier(T entity);

	/**
	 * 判断是否为托管状态
	 * 
	 * @param entity
	 *            实体
	 * @return 是否为托管状态
	 */
	boolean contains(T entity);

	/**
	 * 持久化实体
	 * 
	 * @param entity
	 *            实体
	 */
	void persist(T entity);

	/**
	 * 合并实体
	 * 
	 * @param entity
	 *            实体
	 * @return 实体
	 */
	T merge(T entity);

	/**
	 * 游离实体
	 * 
	 * @param entity
	 *            实体
	 */
	void detach(T entity);

	/**
	 * 移除实体
	 * 
	 * @param entity
	 *            实体
	 */
	void remove(T entity);

	/**
	 * 锁定实体
	 * 
	 * @param entity
	 *            实体
	 * @param lockModeType
	 *            锁定方式
	 */
	void lock(T entity, LockModeType lockModeType);

	/**
	 * 刷新实体
	 * 
	 * @param entity
	 *            实体
	 * @param lockModeType
	 *            锁定方式
	 */
	void refresh(T entity, LockModeType lockModeType);

	/**
	 * 清除缓存
	 */
	void clear();

	/**
	 * 刷新数据
	 */
	void flush();
}
