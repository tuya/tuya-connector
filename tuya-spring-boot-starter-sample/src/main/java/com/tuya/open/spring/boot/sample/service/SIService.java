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

    String DEVICE_ID = "6c8561a54bf607698f2sgw";

    public void isDeviceExist(String deviceId) {
        Result<DeviceDetail> result = thingConnector.getDeviceResult(DEVICE_ID);
        System.out.println(JSON.toJSONString(result));
    }

    public void getDevice(String deviceId) {
        DeviceDetail device = thingConnector.getDevice(DEVICE_ID);
        System.out.println(JSON.toJSONString(device));
    }

    public void getFirmware(String deviceId) {
        Object ret = thingConnector.getFirmware(DEVICE_ID);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getExtProperties(String deviceId) {
        List<Map<String, Object>> ret = thingConnector.getDeviceExtProperties(DEVICE_ID);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceSpecification(String deviceId) {
        DeviceSpecification ret = thingConnector.getDeviceSpecification(DEVICE_ID);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceModel(String deviceId) {
        System.out.println(thingConnector.getDeviceModel(DEVICE_ID));
    }

    public void getDeviceProperties(String deviceId) {
        DeviceProperties ret = thingConnector.getDeviceProperties(DEVICE_ID);
        System.out.println(JSON.toJSONString(ret));
    }

    public void getDeviceState(String deviceId) {
        Map<String, Object> ret = thingConnector.getDeviceState(DEVICE_ID);
        System.out.println(JSON.toJSONString(ret));
    }

    public void issueDeviceProperties(String deviceId, DeviceProperties properties) {
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> kv = new HashMap<>();
        kv.put("switch_led", false);
        param.put("properties", kv);
        System.out.println(thingConnector.issueDeviceProperties(DEVICE_ID, param));
    }

    public void getIndustryDevice(String deviceId) {
        System.out.println(JSON.toJSONString(thingConnector.getIndustryDevice(DEVICE_ID)));
    }

}
