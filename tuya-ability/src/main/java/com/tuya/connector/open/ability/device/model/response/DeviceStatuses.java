package com.tuya.connector.open.ability.device.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: TODO
 *
 * @author Chyern
 * @date 2021/3/31
 */
@Data
public class DeviceStatuses implements Serializable {
    private static final long serialVersionUID = 265266895576284774L;
    private String id;
    private List<DeviceStatus> status;

    @Data
    public static class DeviceStatus implements Serializable {

        private static final long serialVersionUID = -4980052570441016642L;
        private String code;
        private String name;
        private Object value;
    }
}
