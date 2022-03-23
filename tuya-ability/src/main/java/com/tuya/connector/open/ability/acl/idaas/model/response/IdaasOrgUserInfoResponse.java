package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 9:50 AM
 */
@Data
public class IdaasOrgUserInfoResponse {

   private String organizationCode;

   private String uid;

   private String spaceId;

   private Integer status;
}
