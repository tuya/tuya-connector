package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.idaas.model.IdaasPage;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasOrgBindUsersRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasOrgQueryUserRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasOrgUnbindUsersRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasOrgUserInfoResponse;

/**
 * @author Mario
 * @since 2022/1/5 10:18 AM
 */
public interface IdaasOrganizationUserConnector {

    /**
     * 组织下批量添加用户
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organization-users/actions/batch-add")
    Boolean bindUsers(@Path("space_id")String spaceId, @Body IdaasOrgBindUsersRequest req);

    /**
     * 组织下批量移除用户
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organization-users/actions/batch-delete")
    Boolean unbindUsers(@Path("space_id")String spaceId, @Body IdaasOrgUnbindUsersRequest req);

    /**
     * 查询组织下的用户
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/organization-users/actions/query")
    IdaasPage<IdaasOrgUserInfoResponse> queryOrgUsers(@Path("space_id")String spaceId, @Body IdaasOrgQueryUserRequest req);
}
