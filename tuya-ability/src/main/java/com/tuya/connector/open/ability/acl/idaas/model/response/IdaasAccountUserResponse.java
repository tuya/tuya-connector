package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 1:16 PM
 */
@Data
public class IdaasAccountUserResponse {

   private String uid;

   private String userName;

   private String email;

   private String mobile;
}
