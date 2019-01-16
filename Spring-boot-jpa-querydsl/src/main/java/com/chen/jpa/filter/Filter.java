package com.chen.jpa.filter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.chen.jpa.entity.BaseEntity;
import com.chen.jpa.enums.FilterOperatorEnum;

/**
 * 过滤器
 * @author chen
 * @date 2019-01-03 11:16:53
 */
public class Filter implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -5363234287319504888L;

    /** 默认是否忽略大小写 */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /** 属性 */
    private String property;

    /** 运算符 */
    private FilterOperatorEnum operator;

    /** 值 */
    private Object value;

    /** 是否忽略大小写 */
    private boolean ignoreCase;

	/** 等式左边 */
	private Filter lhs;
	
	/** 等式右边 */
	private Filter rhs;

    /**
     * @param property
     *            属性
     * @param operator
     *            运算符
     * @param value
     *            值
     */
    public Filter(String property, FilterOperatorEnum operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = DEFAULT_IGNORE_CASE;
    }

    /**
     * @param property
     *            属性
     * @param operator
     *            运算符
     * @param value
     *            值
     * @param ignoreCase
     *            忽略大小写
     */
    public Filter(String property, FilterOperatorEnum operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }
    
    /**
     * @param lhs
     *            等式左边
     * @param operator
     *            运算符
     * @param rhs
     *            等式右边
     */
    public Filter(Filter lhs, FilterOperatorEnum operator, Filter rhs) {
    	this.lhs = lhs;
    	this.operator = operator;
    	this.rhs = rhs;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public FilterOperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(FilterOperatorEnum operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean getIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public Filter getLhs() {
		return lhs;
	}

	public void setLhs(Filter lhs) {
		this.lhs = lhs;
	}

	public Filter getRhs() {
		return rhs;
	}

	public void setRhs(Filter rhs) {
		this.rhs = rhs;
	}

	/**
     * 生成等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 等于过滤器
     */
    public static Filter eq(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.eq, value);
    }

    /**
     * 生成等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @param ignoreCase
     *            忽略大小写
     * @return 等于过滤器
     */
    public static Filter eq(String property, Object value, boolean ignoreCase) {
        return new Filter(property, FilterOperatorEnum.eq, value, ignoreCase);
    }

    /**
     * 生成不等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 不等于过滤器
     */
    public static Filter ne(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.ne, value);
    }

    /**
     * 生成不等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @param ignoreCase
     *            忽略大小写
     * @return 不等于过滤器
     */
    public static Filter ne(String property, Object value, boolean ignoreCase) {
        return new Filter(property, FilterOperatorEnum.ne, value, ignoreCase);
    }

    /**
     * 生成大于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 大于过滤器
     */
    public static Filter gt(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.gt, value);
    }

    /**
     * 生成小于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 小于过滤器
     */
    public static Filter lt(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.lt, value);
    }

    /**
     * 生成大于等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 大于等于过滤器
     */
    public static Filter ge(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.ge, value);
    }

    /**
     * 生成小于等于过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 小于等于过滤器
     */
    public static Filter le(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.le, value);
    }

    /**
     * 生成相似过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 相似过滤器
     */
    public static Filter like(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.like, value);
    }

    /**
     * 生成包含过滤器
     * 
     * @param property
     *            属性
     * @param value
     *            值
     * @return 包含过滤器
     */
    public static Filter in(String property, Object value) {
        return new Filter(property, FilterOperatorEnum.in, value);
    }

    /**
     * 生成为Null过滤器
     * 
     * @param property
     *            属性
     * @return 为Null过滤器
     */
    public static Filter isNull(String property) {
        return new Filter(property, FilterOperatorEnum.isNull, null);
    }

    /**
     * 生成不为Null过滤器
     * 
     * @param property
     *            属性
     * @return 不为Null过滤器
     */
    public static Filter isNotNull(String property) {
        return new Filter(property, FilterOperatorEnum.isNotNull, null);
    }

    /**
     * 生成"并且"过滤器
     * 
     * @param lhs
     *            等式左边
     * @param rhs
     *            等式右边
     * @return "并且"过滤器
     */
    public static Filter and(Filter lhs, Filter rhs) {
    	return new Filter(lhs, FilterOperatorEnum.and, rhs);
    }

    /**
     * 生成"或者"过滤器
     * 
     * @param lhs
     *            等式左边
     * @param rhs
     *            等式右边
     * @return "或者"过滤器
     */
    public static Filter or(Filter lhs, Filter rhs) {
    	return new Filter(lhs, FilterOperatorEnum.or, rhs);
    }
    
    /**
     * @param criteriaBuilder 
     * @return
     */
    public Predicate getRestriction(CriteriaBuilder criteriaBuilder, Root<? extends BaseEntity> root) {
    	if(isEmpty()) {
    		return null;
    	}
        switch (getOperator()) {
            case /** 等于 */
            eq: {
                if (getIgnoreCase() && getValue() instanceof String) {
                    return criteriaBuilder.equal(criteriaBuilder.lower(root.<String> get(getProperty())), ((String) getValue()).toLowerCase());
                } else {
                	return criteriaBuilder.equal(root.get(getProperty()), getValue());
                }
            }
            case /** 不等于 */
            ne: {
                if (getIgnoreCase() && getValue() instanceof String) {
                	return criteriaBuilder.notEqual(criteriaBuilder.lower(root.<String> get(getProperty())), ((String) getValue()).toLowerCase());
                } else {
                	return criteriaBuilder.notEqual(root.get(getProperty()), getValue());
                }
            }
            case /** 大于 */
            gt: {
                if (getValue() instanceof Number) {
                	return criteriaBuilder.gt(root.<Number> get(getProperty()), (Number) getValue());
                } else if (getValue() instanceof Date) {
                	return criteriaBuilder.greaterThan(root.<Date> get(getProperty()), (Date) getValue());
                }
                break;
            }
            case /** 大于等于 */
            ge: {
                if (getValue() instanceof Number) {
                	return criteriaBuilder.ge(root.<Number> get(getProperty()), (Number) getValue());
                } else if (getValue() instanceof Date) {
                	return criteriaBuilder.greaterThanOrEqualTo(root.<Date> get(getProperty()), (Date) getValue());
                }
                break;
            }
            case /** 小于 */
            lt: {
                if (getValue() instanceof Number) {
                	return criteriaBuilder.lt(root.<Number> get(getProperty()), (Number) getValue());
                } else if (getValue() instanceof Date) {
                	return criteriaBuilder.lessThan(root.<Date> get(getProperty()), (Date) getValue());
                }
                break;
            }
            case /** 小于等于 */
            le: {
                if (getValue() instanceof Number) {
                	return criteriaBuilder.le(root.<Number> get(getProperty()), (Number) getValue());
                } else if (getValue() instanceof Date) {
                	return criteriaBuilder.lessThanOrEqualTo(root.<Date> get(getProperty()), (Date) getValue());
                }
                break;
            }
            case /** 相似 */
            like: {
            	return criteriaBuilder.like(root.<String> get(getProperty()), "%" + (String) getValue() + "%");
            }
            case /** 包含 */
            in: {
            	return root.get(getProperty()).in(getValue());
            }
            case /** 为NULL */
            isNull: {
            	return root.get(getProperty()).isNull();
            }
            case /** 不为NULL */
            isNotNull: {
            	return root.get(getProperty()).isNotNull();
            }
            case /** 并且 */
            and: {
            	return criteriaBuilder.and(getLhs().getRestriction(criteriaBuilder, root), getRhs().getRestriction(criteriaBuilder, root));
            }
            case /** 或者 */
            or: {
            	return criteriaBuilder.or(getLhs().getRestriction(criteriaBuilder, root), getRhs().getRestriction(criteriaBuilder, root));
            }
            default: {
                break;
            }
        }
        return null;
    }

    /**
     * 生成HashCode
     * 
     * @return HashCode
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }

    /**
     * 判断是否相等
     * 
     * @param obj
     *            对象
     * @return 是否相等
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (isEmpty() || obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Filter filter = (Filter) obj;
        if (filter.isEmpty()) {
            return false;
        }
		return new EqualsBuilder().append(getProperty(), filter.getProperty())
                .append(getOperator(), filter.getOperator()).append(getValue(), filter.getValue()).isEquals();
    }

    /**
     * 判断是否为空
     * 
     * @return 是否为空
     */
    public boolean isEmpty() {
        return /*StringUtils.isBlank(getProperty()) || */getOperator() == null;
    }

}