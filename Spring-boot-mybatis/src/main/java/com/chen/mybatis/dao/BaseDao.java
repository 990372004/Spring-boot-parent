package com.chen.mybatis.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * dao --基类
 * @author chen
 * @date 2018-12-27 02:58:04
 * @param <T>
 * @param <U>
 * @param <ID>
 */
public interface BaseDao<T, U, ID extends Serializable> {
	/**
	 * 根据条件查询总数量
	 * 
	 * @param example
	 * @return
	 */
	long countByExample(U example);
	/**
	 * 根据条件删除信息
	 * @param example
	 * @return
	 */
	int deleteByExample(U example);

	/**
	 * 根据id删除信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(ID id);

	/**
	 * 插入信息
	 * 
	 * @param record
	 * @return
	 */
	int insert(T record);

	/**
	 * 判空插入信息
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	/**
	 * 根据条件查询集合
	 * 
	 * @param example
	 * @return
	 */
	List<T> selectByExample(U example);

	/**
	 * 根据id查询信息
	 * 
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(ID id);

	/**
	 * 根据条件更新 判空
	 * 
	 * @param record
	 * @param example
	 * @return
	 */
	int updateByExampleSelective(@Param("record") T record, @Param("example") U example);

	/**
	 * 根据条件更新 不判空
	 * 
	 * @param record
	 * @param example
	 * @return
	 */
	 int updateByExample(@Param("record") T record, @Param("example") U example);

	/**
	 * 根据id更新信息 判空
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据id更新信息 不判空
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);
}