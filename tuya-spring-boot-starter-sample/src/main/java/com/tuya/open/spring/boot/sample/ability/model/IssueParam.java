package com.tuya.open.spring.boot.sample.ability.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
public class IssueParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -514297250121261947L;

    private Map<String, Object> properties;
}
