package com.tuya.connector.open.ability.acl.shield.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 5:01 PM
 */
@Data
public class ShieldRolePermissionsInfoResponse {

   private String roleCode;

   private List<ShieldPermissionInfoResponse> permissions;
}
