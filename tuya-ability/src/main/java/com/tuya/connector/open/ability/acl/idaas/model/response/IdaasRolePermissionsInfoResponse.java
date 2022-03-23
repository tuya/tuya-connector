package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 11:06 AM
 */
@Data
public class IdaasRolePermissionsInfoResponse {

   private String roleCode;

   private List<IdaasPermissionInfoResponse> permissionList;
}
