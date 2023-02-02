package com.tuya.connector.open.ability.device.model.request;

import lombok.Data;

@Data
public class ScheduleListRequest {

    private Long effective_time;
    private Long invalid_time;
    private Integer working_day;
}
