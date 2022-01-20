package com.tuya.open.spring.boot.sample.web;

import com.tuya.connector.open.ability.device.model.request.DeviceCommandRequest;
import com.tuya.connector.open.ability.device.model.request.DeviceModifyRequest;
import com.tuya.connector.open.ability.device.model.response.Devices;
import com.tuya.open.spring.boot.sample.ability.model.Device;
import com.tuya.open.spring.boot.sample.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @GetMapping("/{device_id}")
    public Devices.Device getById(@PathVariable("device_id") String deviceId) {
        return deviceService.getById(deviceId);
    }

    @PostMapping("/{device_id}")
    public Boolean updateName(@PathVariable("device_id") String deviceId, @RequestBody DeviceModifyRequest request) {
        return deviceService.updateName(deviceId, request);
    }

    @PostMapping("/{device_id}/commands")
    public Boolean commands(@PathVariable("device_id") String deviceId, @RequestBody List<DeviceCommandRequest.Command> commands) {
        DeviceCommandRequest request = new DeviceCommandRequest();
        request.setCommands(commands);
        return deviceService.commands(deviceId, request);
    }

}
