package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.idaas.model.request.OrgRequest;

public interface OrganizationConnectorIdaas   {

    @POST("/v1.0/iot-03/idaas-prod/spaces/{space_id}/organizations")
    Boolean addOrg(@Path("space_id") String spaceId, @Body OrgRequest request);
}
