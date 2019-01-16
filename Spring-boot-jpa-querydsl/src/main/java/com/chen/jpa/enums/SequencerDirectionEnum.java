package com.chen.jpa.enums;

/**
 * Enum - 定序器方向
 * @author chen
 * @date 2019-01-03 11:27:37
 */
public enum SequencerDirectionEnum {

    /** 递增 */
    asc,

    /** 递减 */
    desc;

    /**
     * 从String中获取Direction
     * 
     * @param value
     *            值
     * @return String对应的Direction
     */
    public static SequencerDirectionEnum fromString(String value) {
        return SequencerDirectionEnum.valueOf(value.toLowerCase());
    }
}
