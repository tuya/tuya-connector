package com.tuya.connector.open.ability.acl.shield.ability;

import com.tuya.connector.open.ability.acl.shield.model.request.ShieldAddOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldDeleteOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldQueryOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUpdateOrgRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldOrgInfoResponse;
import com.tuya.connector.open.ability.common.AbilitySizePage;

/**
 * @author Mario
 * @since 2022/1/4 2:27 PM
 */
public interface ShieldOrganizationAbility {

   Boolean addOrg(ShieldAddOrgRequest req);

   Boolean deleteOrg(ShieldDeleteOrgRequest req);

   Boolean updateOrg(ShieldUpdateOrgRequest req);

   AbilitySizePage<ShieldOrgInfoResponse> query(ShieldQueryOrgRequest req);
}
