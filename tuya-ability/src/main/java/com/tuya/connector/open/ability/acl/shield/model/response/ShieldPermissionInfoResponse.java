package com.tuya.connector.open.ability.acl.shield.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/10 4:49 PM
 */
@Data
public class ShieldPermissionInfoResponse {

   private String permissionCode;

   private String permissionName;

   private Integer permissionType;

   private String parentCode;

   private Integer order;

   private String remark;
}
