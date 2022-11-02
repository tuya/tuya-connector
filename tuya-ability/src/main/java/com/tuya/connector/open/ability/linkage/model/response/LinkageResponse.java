package com.tuya.connector.open.ability.linkage.model.response;

import com.tuya.connector.open.ability.linkage.model.Action;
import com.tuya.connector.open.ability.linkage.model.Condition;
import lombok.Data;

import java.util.List;

/**
 * LinkageResponse
 *
 * @author jwsong
 * @since 2022/10/31 2:57 下午
 */
@Data
public class LinkageResponse {
    private String id;
    private String status;
    private String runningMode;
    private String spaceId;
    private String name;
    private String type;
    private String decisionExpr;
    private List<Condition> conditions;
    private List<Action> actions;
    private String effectiveTime;
}
