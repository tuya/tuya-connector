package com.tuya.connector.open.ability.linkage.model;

import lombok.Data;

/**
 * EffectiveTime
 *
 * @author jwsong
 * @since 2022/11/15 3:11 下午
 */
@Data
public class EffectiveTime {
    private String start;
    private String end;
    private String loops;
    private String timezoneId;
}
