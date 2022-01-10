package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:24 PM
 */
@Data
public class ShieldBatchAddPermissionsRequest {

   private List<ShieldAddPermissionRequest> permissions;
}
