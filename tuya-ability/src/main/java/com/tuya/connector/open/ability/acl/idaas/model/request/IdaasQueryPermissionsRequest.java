package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:50 AM
 */
@Data
public class IdaasQueryPermissionsRequest {

   private List<String> permissionCodeList;
}
