package com.tuya.connector.open.api.abilities;

import com.tuya.connector.open.api.model.Device;

import java.util.Map;

/**
 * <p> TODO
 *
 * @author 丘枫（余秋风 qiufeng.yu@tuya.com）
 * @since 2021/2/18 10:52 上午
 */
public interface DeviceAbility {

    Device getById(String deviceId);

    Boolean commands(String deviceId, Map<String, Object> commands);

    Object specification(String deviceId);
}
