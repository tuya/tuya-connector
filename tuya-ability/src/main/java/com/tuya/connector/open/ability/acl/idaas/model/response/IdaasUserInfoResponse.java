package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:07 AM
 */
@Data
public class IdaasUserInfoResponse {

   String uid;

   String username;

   String remark;

   String spaceId;

   String roleCode;

   String roleName;

   String gmt_create;
}
