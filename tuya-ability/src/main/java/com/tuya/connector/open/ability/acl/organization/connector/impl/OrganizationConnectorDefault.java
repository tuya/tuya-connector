package com.tuya.connector.open.ability.acl.organization.connector.impl;

import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.open.ability.acl.organization.model.request.OrgRequest;

public interface OrganizationConnectorDefault {
    @POST("/v1.0/iot-02/acl/organizations")
    Boolean addOrg(String projectCode, String tenantCode, OrgRequest request);
}
