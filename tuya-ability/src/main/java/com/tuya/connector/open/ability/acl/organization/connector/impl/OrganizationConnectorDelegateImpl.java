package com.tuya.connector.open.ability.acl.organization.connector.impl;

import com.tuya.connector.open.ability.acl.base.AclProviderSelector;
import com.tuya.connector.open.ability.acl.base.AclProviderSelectorImpl;
import com.tuya.connector.open.ability.acl.organization.connector.OrganizationConnector;
import com.tuya.connector.open.ability.acl.organization.model.request.OrgRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationConnectorDelegateImpl implements OrganizationConnector {

    @Autowired
    private OrganizationConnectorDefault connectorDefault;

    @Autowired
    private OrganizationConnectorIdaas connectorIdaas;

    @Autowired
    private AclProviderSelector aclProviderSelector;

    @Override
    public Boolean addOrg(String projectCode, String tenantCode, OrgRequest request) {
        return addOrgAdapter(projectCode, tenantCode, request);
    }


    private boolean addOrgAdapter(String projectCode, String tenantCode, OrgRequest request) {
        String providerCode = aclProviderSelector.selectProvider(projectCode);
        //todo convert spaceId
        String spaceId = "";
        if (AclProviderSelectorImpl.IDAAS.equals(providerCode)) {
            return connectorIdaas.addOrg(spaceId, request);
        }
        return connectorDefault.addOrg(projectCode, tenantCode, request);
    }


}
