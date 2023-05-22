package com.tuya.open.spring.boot.sample.ability.api;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.open.spring.boot.sample.ability.model.Device;

import java.util.Map;

public interface DeviceConnector {
    @GET("/v1.1/iot-03/devices/{device_id}")
    Device getById(@Path("device_id") String deviceId);

    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean command(@Path("device_id") String deviceId, @Body Map<String, Object> commands);
}
