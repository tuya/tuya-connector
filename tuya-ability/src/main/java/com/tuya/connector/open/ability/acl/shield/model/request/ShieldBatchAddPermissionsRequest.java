package com.tuya.connector.open.ability.acl.shield.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:24 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShieldBatchAddPermissionsRequest {

   @SerializedName("permissions")
   private List<ShieldAddPermissionRequest> permissions;
}
