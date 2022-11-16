package com.tuya.connector.open.ability.linkage.model;

import com.tuya.connector.open.ability.linkage.model.request.LinkageAddRequest;
import lombok.Data;

/**
 * ConditionRequest
 *
 * @author jwsong
 * @since 2022/11/1 11:21 上午
 */
@Data
public class Condition {
    private String entityId;
    private String entityType;
    private Integer code;
    private Expr expr;
}
