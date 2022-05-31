package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldRoleBatchAddPermissionsRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldRoleCheckPermissionRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldRolePermissionsInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:56 PM
 */
public interface ShieldRolePermissionConnector {

   @POST("/v1.0/iot-02/roles/{role_code}/permissions")
   Boolean batchAddRolePermissions(@Path("role_code") String roleCode,
                                   @Body ShieldRoleBatchAddPermissionsRequest req);

   @POST("/v1.0/iot-02/roles/{role_code}/actions/permission-valid")
   Boolean checkRolePermission(@Path("role_code") String roleCode,
                               @Body ShieldRoleCheckPermissionRequest req);


   @GET("/v1.0/iot-02/roles-permissions")
   List<ShieldRolePermissionsInfoResponse> queryRolesPermissions(@Query("role_codes") String roleCodes);

}
