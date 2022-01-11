package com.tuya.open.spring.boot.sample.ability.api;

import com.tuya.connector.api.annotations.*;
import com.tuya.connector.open.api.token.TuyaToken;
import com.tuya.open.spring.boot.sample.ability.model.Device;

import java.util.Map;

/**
 * <p> TODO
 *
 * @author @author qiufeng.yu@tuya.com
 * @since 2021/4/1 10:10 下午
 */
public interface DeviceConnector {

    @GET("/v1.0/iot-03/devices/{device_id}")
    Device getById(@Path("device_id") String deviceId);

    @PUT("/v1.0/iot-03/devices/{device_id}")
    Boolean update(@Path("device_id") String deviceId, @Body Device device);

    @POST("/v1.0/iot-03/devices/{device_id}/commands")
    Boolean commands(@Path("device_id") String deviceId, @Body Map<String, Object> commands);

    @GET("/v1.0/token/{refresh_token}")
    TuyaToken refreshToken(@Path("refresh_token") String refreshToken);
}
