package com.tuya.open.spring.boot.sample.service;

import com.tuya.open.spring.boot.sample.ability.api.DeviceConnector;
import com.tuya.open.spring.boot.sample.ability.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/4/1 10:13 下午
 */
import java.util.List;

@SuppressWarnings("all")
@Service
public class DeviceService {
    @Autowired
    DeviceConnector devicesConnector;

    public Device getById(String deviceId) {
        return devicesConnector.getById(deviceId);
    }

    public Boolean command(String deviceId, List<Map<String, Object>> commands) {
        return devicesConnector.command(deviceId, Map.of("commands", commands));
    }

}
