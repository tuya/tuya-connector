package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.IdaasPage;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasCreateUserRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryUserRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasPermissionInfoResponse;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasUserInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:03 AM
 */
public interface IdaasUserConnector {

   /**
    * 创建用户
    */
   @POST("/v1.0/iot-03/idaas/spaces/{space_id}/users")
   Boolean createUser(@Path("space_id")String spaceId, IdaasCreateUserRequest req);

   /**
    * 删除用户
    */
   @DELETE("/v1.0/iot-03/idaas/spaces/{space_id}/users/{uid}")
   Boolean deleteUser(@Path("space_id")String spaceId, @Path("uid")String uid);

   /**
    * 查询用户
    */
   @GET("/v1.0/iot-03/idaas/spaces/{space_id}/users/{uid}")
   IdaasUserInfoResponse queryUserByUid(@Path("space_id")String spaceId, @Path("uid")String uid);

   /**
    * 分页查询用户
    */
   @POST("/v1.0/iot-03/idaas/spaces/{space_id}/page-user")
   IdaasPage<IdaasUserInfoResponse> pageQuery(@Path("space_id")String spaceId, @Body IdaasQueryUserRequest req);

   /**
    * 查询用户的权限
    */
   @GET("/v1.0/iot-03/idaas/spaces/{space_id}/users/{uid}/permissions")
   List<IdaasPermissionInfoResponse> queryUserPermissions(@Path("space_id")String spaceId, @Path("uid")String uid);

   /**
    * 校验用户权限
    */
   @POST("/v1.0/iot-03/idaas/valid-user-permission")
   Boolean validUserPermission(@Query("spaceId") String spaceId, @Query("permission_code") String permissionCode, @Query("uid") String uid);
}
