package com.tuya.connector.open.ability.acl.shield.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/10 4:39 PM
 */
@Data
public class ShieldAddPermissionRequest {

    @SerializedName("permissionCode")
    private String permissionCode;

    @SerializedName("permissionName")
    private String permissionName;

    @SerializedName("permissionType")
    private Integer permissionType;

    @SerializedName("parentCode")
    private String parentCode;

    private Integer order;

    private String remark;
}
