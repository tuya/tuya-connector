package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.open.ability.acl.idaas.model.request.OrgRequest;

public interface OrganizationConnectorDefault {
    @POST("/v1.0/iot-02/acl/organizations")
    Boolean addOrg(OrgRequest request);
}
