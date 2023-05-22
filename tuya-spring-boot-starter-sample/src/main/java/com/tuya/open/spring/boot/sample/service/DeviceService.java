package com.tuya.open.spring.boot.sample.service;

import com.tuya.open.spring.boot.sample.ability.api.DeviceConnector;
import com.tuya.open.spring.boot.sample.ability.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class DeviceService {
    @Autowired
    DeviceConnector deviceConnector;

    public Device getById(String deviceId) {
        return deviceConnector.getById(deviceId);
    }

    public Boolean command(String deviceId, List<Map<String, Object>> commands) {
        return deviceConnector.command(deviceId, Map.of("commands", commands));
    }

}
