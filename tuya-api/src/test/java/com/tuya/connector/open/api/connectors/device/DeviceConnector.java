package com.tuya.connector.open.api.connectors.device;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.api.abilities.DeviceAbility;
import com.tuya.connector.open.api.model.Device;

import java.util.Map;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/4 5:53 下午
 */
public interface DeviceConnector extends DeviceAbility {
    /**
     * 设备信息查询
     * @param deviceId
     * @return
     */
    @Override
    @GET("/v1.1/iot-03/devices/{device_id}")
    Device getById(@Path("device_id") String deviceId);

    /**
     * 设备指令下发
     * @param deviceId
     * @param commands
     * @return
     */
    @Override
    @POST("/v1.0/devices/{device_id}/commands")
    Boolean commands(@Path("device_id") String deviceId, @Body Map<String, Object> commands);

    @Override
    @GET("/v1.2/iot-03/devices/{device_id}/specification")
    Object specification(@Path("device_id") String deviceId);
}
