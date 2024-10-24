package com.tuya.open.spring.boot.sample.ability.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceProperties implements Serializable {
    @Serial
    private static final long serialVersionUID = -8949241840263891680L;

    private List<Item> properties;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item implements Serializable {

        @Serial
        private static final long serialVersionUID = 4342920416129210078L;

        private String code;
        private String customName;
        private Integer dpId;
        private Long time;
        private String type;
        private Object value;
    }

}
