package com.tuya.connector.open.ability.acl.shield.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:24 PM
 */
@Data
public class ShieldBatchAddPermissionsRequest {

   @SerializedName("permissions")
   private List<ShieldAddPermissionRequest> permissions;
}
