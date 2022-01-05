package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.IdaasPage;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasAddOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasUpdateOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasOrgInfoResponse;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasOrgTreeInfoResponse;

public interface IdaasOrganizationConnector {

    /**
     * 新增组织
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organizations")
    Boolean addOrg(@Path("space_id") String spaceId, @Body IdaasAddOrgRequest request);

    /**
     * 新增组织(同时添加组织类型的用户)
     */
    @POST("/v1.0/iot-03/idaas-prod/spaces/{space_id}/organizations")
    Boolean addOrgProd(@Path("space_id") String spaceId, @Body IdaasAddOrgRequest request);

    /**
     * 删除组织
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/actions/soft-delete")
    Boolean deleteOrg(@Path("space_id") String spaceId, @Query("uid") String uid);

    /**
     * 更新组织
     */
    @PUT("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/{code}")
    Boolean updateOrg(@Path("space_id") String spaceId, @Path("code") String code, @Query("uid") String uid, @Body IdaasUpdateOrgRequest req);

    /**
     * 查询组织信息
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/actions/query")
    IdaasPage<IdaasOrgInfoResponse> query(@Path("space_id") String spaceId, @Body IdaasQueryOrgRequest req);

    /**
     * 查询组织详情
     */
    @GET("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/{code}")
    IdaasOrgInfoResponse queryDetail(@Path("space_id") String spaceId, @Path("code") String code);

    /**
     * 查询组织树
     */
    @GET("/v1.0/iot-03/idaas/spaces/{space_id}/organizations")
    IdaasOrgTreeInfoResponse queryTree(@Path("space_id") String spaceId);
}
