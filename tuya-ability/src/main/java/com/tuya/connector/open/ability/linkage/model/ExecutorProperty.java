package com.tuya.connector.open.ability.linkage.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * ExecutorProperty
 *
 * @author jwsong
 * @since 2022/11/14 5:50 下午
 */
@Data
public class ExecutorProperty {
    private String functionCode;
    private Object functionValue;
    private Integer delaySeconds;
}
