package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:05 AM
 */
@Data
public class IdaasCreateUserRequest {

   String uid;

   String username;

   String remark;
}
