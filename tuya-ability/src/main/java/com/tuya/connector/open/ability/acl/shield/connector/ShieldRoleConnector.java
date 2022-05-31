package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldAddRoleRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUpdateRoleRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldRoleInfoResponse;
import com.tuya.connector.open.ability.common.Page;

/**
 * @author Mario
 * @since 2022/1/10 3:43 PM
 */
public interface ShieldRoleConnector {

   @POST("/v1.0/iot-02/roles")
   Boolean addRole(@Body ShieldAddRoleRequest req);

   @DELETE("/v1.0/iot-02/roles/{role_code}")
   Boolean deleteRole(@Path("role_code") String roleCode);

   @PUT("/v1.0/iot-02/roles/{role_code}")
   Boolean updateRole(@Path("role_code") String roleCode,
                      @Body ShieldUpdateRoleRequest req);

   @GET("/v1.0/iot-02/roles")
   Page<ShieldRoleInfoResponse> page(@Query("role_codes") String roleCodes,
                                     @Query("role_name") String roleName,
                                     @Query("page_no") Integer pageNo,
                                     @Query("page_size") Integer pageSize,
                                     @Query("gmt_modified_asc") Boolean gmtModifiedAsc);

   @GET("/v1.0/iot-02/roles")
   Page<ShieldRoleInfoResponse> pageList(@Query("role_codes") String roleCodes,
                                     @Query("role_name") String roleName,
                                     @Query("dimension_no") String dimensionNo,
                                     @Query("page_no") Integer pageNo,
                                     @Query("page_size") Integer pageSize,
                                     @Query("gmt_modified_asc") Boolean gmtModifiedAsc);

   @GET("/v1.0/iot-02/roles/{role_code}")
   ShieldRoleInfoResponse queryByCode(@Path("role_code") String roleCode);
}
