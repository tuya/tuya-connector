package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldOrgAddUsersRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldOrgUserInfoResponse;
import com.tuya.connector.open.ability.common.Page;

/**
 * @author Mario
 * @since 2022/1/10 3:30 PM
 */
public interface ShieldOrganizationUserConnector {

   @POST("/v1.0/iot-02/organizations/{organization_code}/users")
   Boolean batchAddUsers(@Path("organization_code") String orgCode,
                         @Body ShieldOrgAddUsersRequest req);

   @DELETE("/v1.0/iot-02/organizations/{organization_code}/users")
   Boolean batchDeleteUsers(@Path("organization_code") String orgCode,
                            @Query("uids") String uids);

   @GET("/v1.0/iot-02/organizations/{organization_code}/users")
   Page<ShieldOrgUserInfoResponse> pageUsers(@Path("organization_code") String orgCode,
                                             @Query("uid") String uid,
                                             @Query("page_no") Integer pageNo,
                                             @Query("page_size") Integer pageSize);
}
