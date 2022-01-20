package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldAddOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUpdateOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldOrgInfoResponse;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldOrgTreeInfoResponse;
import com.tuya.connector.open.ability.common.Page;

public interface ShieldOrganizationConnector {

    @POST("/v1.0/iot-02/organizations")
    String addOrg(ShieldAddOrgRequest req);

    @DELETE("/v1.0/iot-02/organizations/{organization_id}")
    Boolean deleteOrg(@Path("organization_id") String orgCode, @Query("uid") String uid);

    @PUT("/v1.0/iot-02/organizations/{organization_id}")
    Boolean updateOrg(@Path("organization_id") String orgCode, @Body ShieldUpdateOrgRequest req);

    @GET("/v1.0/iot-02/organizations")
    Page<ShieldOrgInfoResponse> page(@Query("organization_ids") String orgIds,
                                     @Query("organization_name") String orgName,
                                     @Query("parent_id") String parentId,
                                     @Query("page_no") Integer pageNo,
                                     @Query("page_size") Integer pageSize);

    @GET("/v1.0/iot-02/organizations/{organization_id}")
    ShieldOrgInfoResponse queryByCode(@Path("organization_id") String ortId);

    @GET("/v1.0/iot-02/organizations-tree")
    ShieldOrgTreeInfoResponse tree(@Query("organization_id") String orgId,
                                   @Query("organization_name") String orgName);
}
