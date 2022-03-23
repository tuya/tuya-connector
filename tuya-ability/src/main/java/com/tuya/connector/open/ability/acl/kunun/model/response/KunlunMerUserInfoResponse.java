package com.tuya.connector.open.ability.acl.kunun.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/20 10:30 AM
 */
@Data
public class KunlunMerUserInfoResponse {

   private String userName;

   private String countryCode;

   private String userId;

   private String userNickName;

   private String accountType;

   private Long createTime;

   private String tenantCode;
}
