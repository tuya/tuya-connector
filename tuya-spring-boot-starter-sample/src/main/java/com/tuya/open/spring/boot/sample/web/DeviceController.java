package com.tuya.open.spring.boot.sample.web;

import com.tuya.open.spring.boot.sample.ability.model.Device;
import com.tuya.open.spring.boot.sample.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @GetMapping("{device_id}")
    public Device getById(@PathVariable("device_id") String deviceId) {
        return deviceService.getById(deviceId);
    }

    @PostMapping("{device_id}/command")
    public Boolean command(@PathVariable("device_id") String deviceId, @RequestBody List<Map<String, Object>> commands) {
        return deviceService.command(deviceId, commands);
    }

}
