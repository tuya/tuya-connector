package com.tuya.open.spring.boot.sample.service;

import com.alibaba.fastjson.JSON;
import com.tuya.open.spring.boot.sample.ability.model.DeviceProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SIServiceTest {

    @InjectMocks
    SIService siService;

    String deviceId = "6c8561a54bf607698f2sgw";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fastjson_test() {
        Map<String, Object> m =new HashMap<>();
        Long[] longs = {17201088000000L};
        m.put("fieldValues", longs);
        System.out.println(JSON.toJSONString(m));
    }

    @Test
    void isDeviceExist_validDeviceId_returnsResult() {
        siService.isDeviceExist(deviceId);
    }

    @Test
    void getDevice_validDeviceId_returnsDeviceDetail() {
        siService.getDevice(deviceId);
    }

    @Test
    void getFirmware_validDeviceId_returnsFirmware() {
        siService.getFirmware(deviceId);
    }

    @Test
    void getExtProperties_validDeviceId_returnsProperties() {
        siService.getExtProperties(deviceId);
    }

    @Test
    void getDeviceSpecification_validDeviceId_returnsSpecification() {
        siService.getDeviceSpecification(deviceId);
    }

    @Test
    void getDeviceModel_validDeviceId_returnsModel() {
        siService.getDeviceModel(deviceId);
    }

    @Test
    void getDeviceProperties_validDeviceId_returnsProperties() {
        siService.getDeviceProperties(deviceId);
    }

    @Test
    void getDeviceState_validDeviceId_returnsState() {
        siService.getDeviceState(deviceId);
    }

    @Test
    void issueDeviceProperties_validDeviceIdAndProperties_issuesProperties() {
        DeviceProperties properties = new DeviceProperties();
        siService.issueDeviceProperties(deviceId, properties);
    }

    @Test
    void getIndustryDevice_validDeviceId_returnsIndustryDevice() {
        siService.getIndustryDevice(deviceId);
    }
}