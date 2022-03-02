package com.tuya.connector.open.ability.acl.shield.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mario
 * @since 2022/1/10 4:39 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
