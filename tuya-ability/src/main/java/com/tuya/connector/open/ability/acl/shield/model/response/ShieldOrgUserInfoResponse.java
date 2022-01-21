package com.tuya.connector.open.ability.acl.shield.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/10 3:41 PM
 */
@Data
public class ShieldOrgUserInfoResponse {

   private String organizationId;

   private String uid;

   private Long gmtCreate;

   private Long gmtModified;
}
