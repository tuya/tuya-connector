package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:24 AM
 */
@Data
public class IdaasUserUpdateRoleRequest {

   String uid;

   List<String> roleCodeList;

   String spaceId;
}
