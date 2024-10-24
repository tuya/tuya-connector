package com.tuya.open.spring.boot.sample.ability.api;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.model.Result;
import com.tuya.open.spring.boot.sample.ability.model.DeviceDetail;
import com.tuya.open.spring.boot.sample.ability.model.DeviceProperties;
import com.tuya.open.spring.boot.sample.ability.model.DeviceSpecification;
import com.tuya.open.spring.boot.sample.ability.model.Firmware;

import java.util.List;
import java.util.Map;

public interface ThingConnector {
    @GET("/v2.0/cloud/thing/{device_id}")
    Result<DeviceDetail> getDeviceResult(@Path("device_id") String deviceId);

    @GET("/v2.0/cloud/thing/{device_id}")
    DeviceDetail getDevice(@Path("device_id") String deviceId);

    @GET("/v1.1/iot-03/devices/{device_id}")
    DeviceDetail getIndustryDevice(@Path("device_id") String deviceId);

    @GET("/v2.0/cloud/thing/{device_id}/firmware")
    List<Firmware> getFirmware(@Path("device_id") String deviceId);

    @GET("/v1.0/iot-03/devices/{device_id}/properties")
    List<Map<String, Object>> getDeviceExtProperties(@Path("device_id") String deviceId);

    @GET("/v1.0/iot-03/devices/{device_id}/specification")
    DeviceSpecification getDeviceSpecification(@Path("device_id") String deviceId);

    @GET("/v2.0/cloud/thing/{device_id}/model")
    Map<String, String> getDeviceModel(@Path("device_id") String deviceId);

    @GET("/v2.0/cloud/thing/{device_id}/shadow/properties")
    DeviceProperties getDeviceProperties(@Path("device_id") String deviceId);

    @GET("/v2.0/cloud/thing/{device_id}/state")
    Map<String, Object> getDeviceState(@Path("device_id") String deviceId);

    @POST("/v2.0/cloud/thing/{device_id}/shadow/properties/issue")
    Result<Void> issueDeviceProperties(@Path("device_id") String deviceId, @Body Map<String, Object> param);
}
