package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/21 2:23 PM
 */
@Data
public class ShieldUpdatePermissionRequest {

    private String permissionName;

    private Integer permissionType;

    private String parentCode;

    private Integer order;

    private String remark;
}
