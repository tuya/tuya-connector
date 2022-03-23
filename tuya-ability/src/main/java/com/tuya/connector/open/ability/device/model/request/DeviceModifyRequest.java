package com.tuya.connector.open.ability.device.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description  TODO
 *
 * @author Chyern
 * @date 2021/3/27
 */
@Data
public class DeviceModifyRequest implements Serializable {

    private static final long serialVersionUID = 155908041842121243L;
    private String name;
}
