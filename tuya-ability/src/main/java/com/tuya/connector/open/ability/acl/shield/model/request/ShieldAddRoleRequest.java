package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/10 3:44 PM
 */
@Data
public class ShieldAddRoleRequest {

   private String roleCode;

   private String roleName;

   private String remark;

   private String dimensionNo;
}
