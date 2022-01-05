package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:41 AM
 */
@Data
public class IdaasCreatePermissionsRequest {

   private List<IdaasCreatePermissionRequest> permissionList;
}
