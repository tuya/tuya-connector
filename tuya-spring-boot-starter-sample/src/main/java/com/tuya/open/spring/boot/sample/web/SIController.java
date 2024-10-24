package com.tuya.open.spring.boot.sample.web;

import com.tuya.open.spring.boot.sample.service.SIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIController {

    @Autowired
    SIService sIService;

    @RequestMapping("/a")
    public void isDeviceExist(String deviceId) {
        sIService.isDeviceExist(deviceId);
    }

    @RequestMapping("/b")
    public void getDevice(String deviceId) {
        sIService.getDevice(deviceId);
    }

    @RequestMapping("/c")
    public void getFirmware(String deviceId) {
        sIService.getFirmware(deviceId);
    }

    @RequestMapping("/d")
    public void getExtProperties(String deviceId) {
        sIService.getExtProperties(deviceId);
    }

    @RequestMapping("/e")
    public void getDeviceSpecification(String deviceId) {
        sIService.getDeviceSpecification(deviceId);
    }

    @RequestMapping("/f")
    public void getDeviceModel(String deviceId) {
        sIService.getDeviceModel(deviceId);
    }

    @RequestMapping("/g")
    public void getDeviceProperties(String deviceId) {
        sIService.getDeviceProperties(deviceId);
    }

    @RequestMapping("/h")
    public void getDeviceState(String deviceId) {
        sIService.getDeviceState(deviceId);
    }

    @RequestMapping("/i")
    public void issue(String deviceId) {
        sIService.issueDeviceProperties(null, null);
    }
}
