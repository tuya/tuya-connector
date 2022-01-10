package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasUserAddRoleRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasUserUpdateRolesRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasRoleInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:20 AM
 */
public interface IdaasUserRoleConnector {

   /**
    * 新增用户角色
    */
   @POST("/v1.0/iot-03/idaas/user-role")
   Boolean addUserRole(@Body IdaasUserAddRoleRequest req);

   /**
    * 删除用户角色
    */
   @DELETE("/v1.0/iot-03/idaas/user-role")
   Boolean deleteUserRole(@Query("role_code") String roleCode, @Query("uid") String uid, @Query("space_id") String spaceId);

   /**
    * 更新用户角色
    */
   @POST("/v1.0/iot-03/idaas/user-batch-role")
   Boolean UpdateUserRole(@Body IdaasUserUpdateRolesRequest req);

   /**
    * 获取单个用户角色
    */
   @GET("/v1.0/iot-03/idaas/spaces/{space_id}/users/{uid}/roles")
   List<IdaasRoleInfoResponse> queryUserRoles(@Path("space_id")String spaceId, @Path("uid")String uid);
}
