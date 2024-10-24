package com.tuya.open.spring.boot.sample.ability.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class Firmware implements Serializable {
    @Serial
    private static final long serialVersionUID = 6845359510017172104L;

    private Integer type;
    private String typeDesc;
    private String currentVersion;
    private Long lastUpgradeTime;
}
