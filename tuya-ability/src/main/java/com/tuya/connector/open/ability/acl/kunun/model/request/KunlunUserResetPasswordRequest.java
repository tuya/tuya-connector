package com.tuya.connector.open.ability.acl.kunun.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/20 10:21 AM
 */
@Data
public class KunlunUserResetPasswordRequest {

   private String username;

   private String newPassword;
}
