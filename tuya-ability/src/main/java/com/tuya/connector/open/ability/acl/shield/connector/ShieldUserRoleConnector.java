package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUserAddRoleRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUserUpdateRolesRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldRoleInfoResponse;
import com.tuya.connector.open.ability.common.Page;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:09 PM
 */
public interface ShieldUserRoleConnector {

   @POST("/v1.0/iot-02/users/{uid}/roles")
   Boolean addUserRole(@Path("uid") String uid,
                       @Body ShieldUserAddRoleRequest req);

   @DELETE("/v1.0/iot-02/users/{uid}/roles")
   Boolean deleteUserRole(@Path("uid") String uid,
                          @Query("role_code") String roleCode);

   @PUT("/v1.0/iot-02/users/{uid}/roles")
   Boolean updateUserRoles(@Path("uid") String uid,
                           @Body ShieldUserUpdateRolesRequest req);

   @GET("/v1.0/iot-02/users/{uid}/roles")
   List<ShieldRoleInfoResponse> queryRolesByUid(@Path("uid") String uid);

   @GET("/v1.0/iot-02/{role_code}/uids")
   Page<String> usersByRoleCode(@Path("role_code") String roleCode);
}
