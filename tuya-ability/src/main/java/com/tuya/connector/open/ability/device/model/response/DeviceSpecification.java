package com.tuya.connector.open.ability.device.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description  TODO
 *
 * @author Chyern
 * @date 2021/3/27
 */
@Data
public class DeviceSpecification implements Serializable {

    private static final long serialVersionUID = -4645014127674068502L;

    @JsonProperty("category")
    private String category;

    @JsonProperty("functions")
    private List<DeviceFunctions> functions;

    @JsonProperty("status")
    private List<DeviceStatus> status;

    @Data
    public static class DeviceFunctions implements Serializable {
        private static final long serialVersionUID = -4612688818929414519L;

        @JsonProperty("code")
        private String code;

        @JsonProperty("desc")
        private String desc;

        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;

        @JsonProperty("values")
        private String values;
    }

    @Data
    public static class DeviceStatus implements Serializable {

        private static final long serialVersionUID = -4980052570441016642L;

        @JsonProperty("code")
        private String code;

        @JsonProperty("name")
        private String name;

        @JsonProperty("type")
        private String type;

        @JsonProperty("values")
        private String values;
    }

}
