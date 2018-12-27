package com.chen.mybatis.service;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 基类 - service
 * @author chen
 * @date 2018-12-27 02:39:27
 * @param <T>
 * @param <U>
 * @param <ID>
 */
public interface BaseService<T, U, ID extends Serializable> {
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
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(ID id);

	/**
	 * 插入信息
	 * 
	 * @param record
	 * @return
	 */
	Integer insert(T record);

	/**
	 * 插入
	 * 
	 * @param record
	 * @return
	 */
	Integer insertSelective(T record);

	/**
	 * 根据条件查询集合
	 * 
	 * @param example
	 * @return
	 */
	List<T> selectByExample(U example);

	/**
	 * 根据id查询
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
	 * 根据id更新
	 * 
	 * @param record
	 * @return
	 */
	Integer updateByPrimaryKeySelective(T record);

	/**
	 * 更新信息
	 * 
	 * @param record
	 * @return
	 */
	Integer updateByPrimaryKey(T record);
	 /**
     * 初始化mapper
     */
    void initMapper();

}
