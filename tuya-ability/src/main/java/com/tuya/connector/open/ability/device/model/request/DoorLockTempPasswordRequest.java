package com.tuya.connector.open.ability.device.model.request;

import lombok.Data;

import java.util.List;

@Data
public class DoorLockTempPasswordRequest {

    private String password;
    private Long effective_time;
    private Long invalid_time;
    private String password_type;
    private String phone;
    private Integer type;
    private String time_zone;
    private String ticket_id;
    private List<ScheduleListRequest> schedule_list;
}
