package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:36 AM
 */
@Data
public class IdaasRoleBindPermissionsRequest {

   private String roleCode;

   private List<String> permissionCodes;

   private String spaceId;
}
