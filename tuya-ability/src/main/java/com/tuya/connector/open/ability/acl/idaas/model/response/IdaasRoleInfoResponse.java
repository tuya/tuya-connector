package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:29 AM
 */
@Data
public class IdaasRoleInfoResponse {

   private String roleCode;

   private String roleName;

   private String remark;

   private String spaceId;
}
