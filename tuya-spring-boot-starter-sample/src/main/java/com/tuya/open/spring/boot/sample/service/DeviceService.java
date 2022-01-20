package com.tuya.open.spring.boot.sample.service;

import com.tuya.connector.open.ability.device.connector.DeviceConnector;
import com.tuya.connector.open.ability.device.model.request.DeviceCommandRequest;
import com.tuya.connector.open.ability.device.model.request.DeviceModifyRequest;
import com.tuya.connector.open.ability.device.model.response.Devices;
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
@Service
public class DeviceService {
    @Autowired
    private DeviceConnector connector;

    public Devices.Device getById(String deviceId) {
        return connector.selectDevice(deviceId);
    }

    public Boolean updateName(String deviceId, DeviceModifyRequest request) {
        return connector.modifyDevice(deviceId, request);
    }

    public Boolean commands(String deviceId, DeviceCommandRequest request) {
        return connector.commandDevice(deviceId, request);
    }

}
