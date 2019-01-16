package com.chen.jpa.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.chen.jpa.entity.BaseEntity;

/**
 * Listener - 创建日期、修改日期处理
 * 
 * @author chen
 * @date 2019-01-02 05:55:03
 */
public class EntityListener {

	/**
	 * 保存前处理
	 * 
	 * @param entity
	 *            基类
	 * 
	 * @param PrePersist
	 *            帮助您在持久化之前自动填充实体属性。
	 */
	@PrePersist
	public void prePersist(BaseEntity entity) {
		entity.setCreateDate(new Date());
		entity.setModifyDate(new Date());
	}

	/**
	 * 更新前处理
	 * 
	 * @param entity
	 *            基类
	 * @param PreUpdate每次更新实体时更新实体的属性
	 */
	@PreUpdate
	public void preUpdate(BaseEntity entity) {
		entity.setModifyDate(new Date());
	}

}