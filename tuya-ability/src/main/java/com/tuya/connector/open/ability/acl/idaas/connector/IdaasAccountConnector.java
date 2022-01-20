package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasAccountUsersResponse;

/**
 * @author Mario
 * @since 2022/1/5 1:14 PM
 */
public interface IdaasAccountConnector {

   /**
    * 查询用户列表
    */
   @GET("/v1.0/idaas/spaces/{space_id}/accounts")
   IdaasAccountUsersResponse queryUsers(@Path("space_id") String spaceId, @Query("uidList") String uidList);
}
