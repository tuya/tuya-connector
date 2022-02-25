package com.tuya.connector.open.ability.acl.shield.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/10 4:39 PM
 */
@Data
public class ShieldAddPermissionRequest {

    @JsonProperty("permissionCode")
    private String permissionCode;

    @JsonProperty("permissionName")
    private String permissionName;

    @JsonProperty("permissionType")
    private Integer permissionType;

    @JsonProperty("parentCode")
    private String parentCode;

    private Integer order;

    private String remark;
}
