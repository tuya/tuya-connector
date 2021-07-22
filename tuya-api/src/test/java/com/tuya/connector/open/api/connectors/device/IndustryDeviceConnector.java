package com.tuya.connector.open.api.connectors.device;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.api.abilities.DeviceAbility;
import com.tuya.connector.open.api.model.Device;

import java.util.List;
import java.util.Map;

public interface IndustryDeviceConnector {
    /**
     * 设备指令下发
     */
    @POST("/v1.0/iot-03/devices/{deviceId}/commands")
    Boolean sendCommands(@Path("deviceId") String deviceId, @Body Object commands);
}
