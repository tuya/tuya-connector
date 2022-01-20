package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasPermissionInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/20 9:42 AM
 */
public interface IdaasUserPermissionConnector {

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
