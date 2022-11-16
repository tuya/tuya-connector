package com.tuya.connector.open.ability.linkage.model;

import lombok.Data;

/**
 * ExprRequest
 *
 * @author jwsong
 * @since 2022/11/1 1:36 下午
 */
@Data
public class Expr {
    private String statusCode;
    private String comparator;
    private Object statusValue;
}
