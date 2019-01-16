package com.chen.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chen.jpa.listener.EntityListener;

/**
 * Entity - 基类
 * @author chen
 * @date 2019-01-03 02:52:02
 */
@EntityListeners(EntityListener.class)
@MappedSuperclass
@DynamicUpdate //自动更新（动态更新）  更新语句字段是不是null，是-忽略，不是-更新
@DynamicInsert
public abstract class BaseEntity implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -3703154275817120085L;

    /** “ID”属性 */
    public static final String ID_PROP = "id";

    /** “创建日期”属性 */
    public static final String CREATE_DATE_PROP = "createDate";

    /** “更新日期”属性 */
    public static final String UPDATE_DATE_PROP = "modifyDate";

    /** ID */
    private Long id;

    /** 创建日期 */
    private Date createDate;

    /** 修改日期 */
    private Date modifyDate;

    //id: 主键   GeneratedValue  主键生成策略  AUTO： JPA自动选择合适的策略，是默认选项； 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(nullable = false)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }


    /**
     * 生成HashCode
     * 
     * @return HashCode
     */
    @Override
    @Transient
    public int hashCode() {
        return 17 + (isEmpty() ? 0 : getId().hashCode() * 31);
    }

    /**
     * 判断是否相等
     * 
     * @param obj
     *            对象
     * @return 是否相等
     */
    @Override
    @Transient
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (isEmpty() || obj == null || !getClass().isAssignableFrom(obj.getClass())) {
            return false;
        }
        BaseEntity entity = (BaseEntity) obj;
        if (entity.isEmpty()) {
            return false;
        }
        return getId().equals(entity.getId());
    }

    /**
     * 判断是否为空
     * 
     * @return 是否为空
     */
    @Transient
    public boolean isEmpty() {
        return getId() == null;
    }

}