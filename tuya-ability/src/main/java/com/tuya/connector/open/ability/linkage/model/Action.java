package com.tuya.connector.open.ability.linkage.model;

import lombok.Data;

import java.util.Map;

/**
 * Action
 *
 * @author jwsong
 * @since 2022/11/1 1:37 下午
 */
@Data
public class Action {
    private String entityId;
    private String actionExecutor;
    private ExecutorProperty executorProperty;
}
