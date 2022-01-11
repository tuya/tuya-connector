package com.tuya.open.spring.boot.sample.web;

import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.open.spring.boot.sample.ability.api.DeviceConnector;
import com.tuya.open.spring.boot.sample.ability.model.Device;
import com.tuya.open.spring.boot.sample.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p> TODO
 *
 * @author qiufeng.yu@tuya.com
 * @since 2021/4/1 10:14 下午
 */
@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @Autowired
    DeviceConnector deviceConnector;

    @GetMapping("/{device_id}")
    public Device getById(@PathVariable("device_id") String deviceId) {
        return deviceService.getById(deviceId);
    }

    @PostMapping("/{device_id}")
    public Boolean updateName(@PathVariable("device_id") String deviceId, @RequestBody Device device) {
        return deviceService.updateName(deviceId, device);
    }

    @PostMapping("/{device_id}/commands")
    public Boolean commands(@PathVariable("device_id") String deviceId, @RequestBody Map<String, Object> commands) {
        return deviceService.commands(deviceId, commands);
    }

    @GetMapping("/token/refresh")
    public TuyaToken refreshToken(String token) {
        return deviceConnector.refreshToken(token);
    }
}
