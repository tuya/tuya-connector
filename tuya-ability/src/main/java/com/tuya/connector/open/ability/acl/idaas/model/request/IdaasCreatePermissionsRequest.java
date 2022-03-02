package com.tuya.connector.open.ability.acl.idaas.model.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:41 AM
 */
@Data
public class IdaasCreatePermissionsRequest {

   @SerializedName("permissionList")
   private List<IdaasCreatePermissionRequest> permissionList;
}
