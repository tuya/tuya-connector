package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.idaas.ability.IdaasOrganizationAbility;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasDeleteOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasOrgInfoResponse;
import com.tuya.connector.open.ability.common.AbilitySizePage;

public interface IdaasOrganizationConnector extends IdaasOrganizationAbility {

    @POST("/v1.0/iot-03/idaas-prod/spaces/{space_id}/organizations")
    @Override
    Boolean addOrg(@Path("space_id") String spaceId, @Body IdaasOrgRequest request);

    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/actions/soft-delete")
    @Override
    Boolean deleteOrg(String spaceId, IdaasDeleteOrgRequest req);

    @PUT("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/{code}")
    @Override
    Boolean updateOrg(String spaceId, String code, IdaasOrgRequest req);

    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organizations/actions/query")
    @Override
    AbilitySizePage<IdaasOrgInfoResponse> query(String spaceId, IdaasQueryOrgRequest req);
}
