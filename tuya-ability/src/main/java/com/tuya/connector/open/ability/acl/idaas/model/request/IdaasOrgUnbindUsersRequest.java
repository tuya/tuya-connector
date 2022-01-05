package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 9:23 AM
 */
@Data
public class IdaasOrgUnbindUsersRequest {

   private List<String> uidList;

   private String organizationCode;
}
