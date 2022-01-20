package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryRolesPermissionsRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasRoleBindPermissionsRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasRolePermissionsInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:33 AM
 */
public interface IdaasRolePermissionConnector {

   /**
    * 批量绑定角色权限
    */
   @POST("/v1.0/iot-03/idaas/role-batch-permission")
   Boolean batchBindUserPermissions(@Body IdaasRoleBindPermissionsRequest req);

   /**
    * 根据角色code列表查询各个角色的权限
    */
   @POST("/v1.0/iot-03/idaas/spaces/{space_id}/get-batch-role-permission")
   List<IdaasRolePermissionsInfoResponse> queryRolesPermissions(@Path("space_id")String spaceId, @Body IdaasQueryRolesPermissionsRequest req);

   /**
    * 确认角色权限
    */
   @GET("/v1.0/iot-03/idaas/valid-role-permission")
   Boolean validRolePermission(@Query("space_id") String spaceId, @Query("role_code") String roleCode, @Query("permission_code") String permissionCode);
}
