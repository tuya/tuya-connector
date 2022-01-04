package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.open.ability.acl.shield.ability.ShieldOrganizationAbility;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldAddOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldDeleteOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldQueryOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUpdateOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldOrgInfoResponse;
import com.tuya.connector.open.ability.common.AbilitySizePage;

public interface ShieldOrganizationConnector extends ShieldOrganizationAbility {

    @Override
    Boolean addOrg(ShieldAddOrgRequest req);

    @Override
    Boolean deleteOrg(ShieldDeleteOrgRequest req);

    @Override
    Boolean updateOrg(ShieldUpdateOrgRequest req);

    @Override
    AbilitySizePage<ShieldOrgInfoResponse> query(ShieldQueryOrgRequest req);
}
