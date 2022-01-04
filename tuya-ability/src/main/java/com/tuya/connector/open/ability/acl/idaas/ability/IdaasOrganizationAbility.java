package com.tuya.connector.open.ability.acl.idaas.ability;

import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasDeleteOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryOrgRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasOrgInfoResponse;
import com.tuya.connector.open.ability.common.AbilitySizePage;

/**
 * @author Mario
 * @since 2022/1/4 2:25 PM
 */
public interface IdaasOrganizationAbility {

   Boolean addOrg(String spaceId, IdaasOrgRequest req);

   Boolean deleteOrg(String spaceId, IdaasDeleteOrgRequest req);

   Boolean updateOrg(String spaceId, String code, IdaasOrgRequest req);

   AbilitySizePage<IdaasOrgInfoResponse> query(String spaceId, IdaasQueryOrgRequest req);
}
