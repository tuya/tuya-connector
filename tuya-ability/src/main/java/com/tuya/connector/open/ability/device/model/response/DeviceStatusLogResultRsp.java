package com.tuya.connector.open.ability.device.model.response;

import lombok.Data;

/**
 * @description: 设备状态日志查询结果
 * @author: jinyun.zhou@tuya.com
 * @create: 2021-07-26 18:50
 **/
@Data
public class DeviceStatusLogResultRsp {
    /**
     * 事件时间
     */
    private Long eventTime;

    /**
     * 功能点
     */
    private String code;

    /**
     * 状态值
     */
    private String value;
}
