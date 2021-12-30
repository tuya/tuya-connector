package com.tuya.connector.open.ability.acl.organization.connector;

import com.tuya.connector.open.ability.acl.organization.model.request.OrgRequest;

public interface OrganizationConnector {

    /**
     * add org
     *
     * @param projectCode
     * @param tenantCode
     * @return
     */
    Boolean addOrg(String projectCode, String tenantCode, OrgRequest request);
}
