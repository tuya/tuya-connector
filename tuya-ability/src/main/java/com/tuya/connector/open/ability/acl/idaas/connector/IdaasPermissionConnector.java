package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasCreatePermissionsRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryPermissionsRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasPermissionInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:37 AM
 */
public interface IdaasPermissionConnector {

   /**
    * 批量创建权限
    */
   @POST("/v1.0/iot-03/idaas/spaces/{space_id}/add-batch-permission")
   Boolean createPermissions(@Path("space_id")String spaceId, @Body IdaasCreatePermissionsRequest req);

   /**
    * 删除权限
    */
   @DELETE("/v1.0/iot-03/idaas/spaces/{space_id}/permissions/{permission_code}")
   Boolean deletePermission(@Path("space_id")String spaceId, @Path("permission_code")String permissionCode);

   /**
    * 根据权限code列表查询权限
    */
   @POST("/v1.0/iot-03/idaas/spaces/{space_id}/get-batch-permission")
   List<IdaasPermissionInfoResponse> queryPermissions(@Path("space_id")String spaceId, @Body IdaasQueryPermissionsRequest req);

}
