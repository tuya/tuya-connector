package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.PUT;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.idaas.model.IdaasPage;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasCreateRoleRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasQueryRoleRequest;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasUpdateRoleRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasRoleInfoResponse;

/**
 * @author Mario
 * @since 2022/1/5 11:14 AM
 */
public interface IdaasRoleConnector {

    /**
     * 创建角色
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/roles")
    Boolean createRole(@Path("space_id") String spaceId, @Body IdaasCreateRoleRequest req);

    /**
     * 删除角色
     */
    @DELETE("/v1.0/iot-03/idaas/spaces/{space_id}/roles/{role_code}")
    Boolean deleteRole(@Path("space_id") String spaceId, @Path("role_code") String roleCode);

    /**
     * 更新角色
     */
    @PUT("/v1.0/iot-03/idaas/spaces/{space_id}/roles/{role_code}")
    Boolean updateRole(@Path("space_id") String spaceId, @Path("role_code")String roleCode, @Body IdaasUpdateRoleRequest req);

    /**
     * 根据角色code查询角色
     */
    @GET("/v1.0/iot-03/idaas/spaces/{space_id}/roles/{role_code}")
    IdaasRoleInfoResponse queryRole(@Path("space_id")String spaceId, @Path("role_code")String roleCode);

    /**
     * 分页查询角色
     */
    @POST("/v1.0/iot-03/idaas/spaces/{space_id}/page-role")
    IdaasPage<IdaasRoleInfoResponse> pageQuery(@Path("space_id")String spaceId, @Body IdaasQueryRoleRequest req);
}
