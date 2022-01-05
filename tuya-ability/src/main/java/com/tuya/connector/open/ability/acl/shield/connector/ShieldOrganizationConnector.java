package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.open.ability.acl.shield.model.request.ShieldAddOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldDeleteOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUpdateOrgRequest;

public interface ShieldOrganizationConnector {

    Boolean addOrg(ShieldAddOrgRequest req);

    Boolean deleteOrg(ShieldDeleteOrgRequest req);

    Boolean updateOrg(ShieldUpdateOrgRequest req);

}
