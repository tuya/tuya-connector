package com.tuya.open.spring.boot.sample.ability.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class DeviceSpecification implements Serializable {
    @Serial
    private static final long serialVersionUID = -8870376569649350814L;

    private String category;
    private List<Item> functions;
    private List<Item> status;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item implements Serializable{
        @Serial
        private static final long serialVersionUID = -7907633673276744529L;
        private String code;
        private String desc;
        private String name;
        private String type;
        private String values;
    }
}
