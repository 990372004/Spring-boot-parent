package com.chen.jpa.enums;

/**
 * Enum - 过滤器运算符
 * @author chen
 * @date 2019-01-03 11:18:03
 */
public enum FilterOperatorEnum {

    /** 等于 */
    eq,

    /** 不等于 */
    ne,

    /** 大于 */
    gt,

    /** 小于 */
    lt,

    /** 大于等于 */
    ge,

    /** 小于等于 */
    le,

    /** 相似 */
    like,

    /** 包含 */
    in,

    /** 为Null */
    isNull,

    /** 不为Null */
    isNotNull,
    
    /** 并且 */
    and,
    
    /** 或者 */
    or;

    /**
     * 从String中获取Operator
     * 
     * @param value
     *            值
     * @return String对应的operator
     */
    public static FilterOperatorEnum fromString(String value) {
        return FilterOperatorEnum.valueOf(value.toLowerCase());
    }
}
