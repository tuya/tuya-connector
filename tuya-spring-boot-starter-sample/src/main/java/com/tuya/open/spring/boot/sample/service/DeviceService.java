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
@Service
public class DeviceService {
    @Autowired
    private DeviceConnector connector;

    public Device getById(String deviceId) {
        return connector.getById(deviceId);
    }

    public Boolean updateName(String deviceId, Device device) {
        return connector.update(deviceId, device);
    }

    public Boolean commands(String deviceId, Map<String, Object> commonds) {
        return connector.commands(deviceId, commonds);
    }

}
