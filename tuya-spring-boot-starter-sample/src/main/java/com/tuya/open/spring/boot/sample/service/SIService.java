package com.tuya.open.spring.boot.sample.service;

import com.alibaba.fastjson.JSON;
import com.tuya.connector.api.model.Result;
import com.tuya.open.spring.boot.sample.ability.api.ThingConnector;
import com.tuya.open.spring.boot.sample.ability.model.DeviceDetail;
import com.tuya.open.spring.boot.sample.ability.model.DeviceProperties;
import com.tuya.open.spring.boot.sample.ability.model.DeviceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SIService {
    @Autowired
    ThingConnector thingConnector;
    
    public void isDeviceExist(String deviceId) {
        Result<DeviceDetail> result = thingConnector.getDeviceResult(deviceId);
        System.out.println(JSON.toJSONString(result));
    }

    public void getDevice(String deviceId) {
        DeviceDetail device = thingConnector.getDevice(deviceId);
        System.out.println(JSON.toJSONString(device));
    }

    public void getFirmware(String deviceId) {
        Object ret = thingConnector.getFirmware(deviceId);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getExtProperties(String deviceId) {
        List<Map<String, Object>> ret = thingConnector.getDeviceExtProperties(deviceId);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceSpecification(String deviceId) {
        DeviceSpecification ret = thingConnector.getDeviceSpecification(deviceId);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceModel(String deviceId) {
        System.out.println(thingConnector.getDeviceModel(deviceId));
    }

    public void getDeviceProperties(String deviceId) {
        DeviceProperties ret = thingConnector.getDeviceProperties(deviceId);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceState(String deviceId) {
        Map<String, Object> ret = thingConnector.getDeviceState(deviceId);
        System.out.println(JSON.toJSONString(ret));
    }

    public void issueDeviceProperties(String deviceId, DeviceProperties properties) {
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> kv = new HashMap<>();
        kv.put("switch_led", false);
        param.put("properties", kv);
        System.out.println(thingConnector.issueDeviceProperties(deviceId, param));
    }

    public void getIndustryDevice(String deviceId) {
        System.out.println(JSON.toJSONString(thingConnector.getIndustryDevice(deviceId)));
    }

}
