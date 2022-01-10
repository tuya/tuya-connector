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
    Boolean addOrg(ShieldAddOrgRequest req);

    @DELETE("/v1.0/iot-02/organizations/{organization_code}")
    Boolean deleteOrg(@Path("organization_code") String orgCode, @Query("uid") String uid);

    @PUT("/v1.0/iot-02/organizations/{organization_code}")
    Boolean updateOrg(@Path("organization_code") String orgCode, @Body ShieldUpdateOrgRequest req);

    @GET("/v1.0/iot-02/organizations")
    Page<ShieldOrgInfoResponse> page(@Query("organization_codes") String orgCodes,
                                     @Query("organization_name") String orgName,
                                     @Query("parent_code") String parentCode,
                                     @Query("page_no") Integer pageNo,
                                     @Query("page_size") Integer pageSize);

    @GET("/v1.0/iot-02/organizations/{organization_code}")
    ShieldOrgInfoResponse queryByCode(@Path("organization_code") String orgCode);

    @GET("/v1.0/iot-02/organizations-tree")
    ShieldOrgTreeInfoResponse tree(@Query("organization_code") String orgCode,
                                   @Query("organization_name") String orgName);
}
