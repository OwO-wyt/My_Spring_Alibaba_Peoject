package com.sensetime.manual.common.enums;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum StatusEnum {
    /**
     * 数据状态枚举
     */
    ACTIVE(0, "有效"),
    INVALID(1, "无效");

    private final Integer value;
    private final String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
