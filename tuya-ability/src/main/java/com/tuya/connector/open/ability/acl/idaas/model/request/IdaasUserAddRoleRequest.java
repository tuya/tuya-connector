package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:26 AM
 */
@Data
public class IdaasUserAddRoleRequest {

   private String uid;

   private String username;

   private String roleCode;

   private String spaceId;
}
