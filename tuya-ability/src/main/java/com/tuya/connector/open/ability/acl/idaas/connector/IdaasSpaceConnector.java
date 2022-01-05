package com.tuya.connector.open.ability.acl.idaas.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.idaas.model.request.IdaasCreateSpaceRequest;
import com.tuya.connector.open.ability.acl.idaas.model.response.IdaasSpaceInfoResponse;

/**
 * @author Mario
 * @since 2022/1/5 2:20 PM
 */
public interface IdaasSpaceConnector {

   @POST("/v1.0/iot-03/idaas/spaces")
   String createSpace(@Body IdaasCreateSpaceRequest req);

   @GET("/v1.0/iot-03/idaas/spaces")
   IdaasSpaceInfoResponse query(@Query("space_group") String spaceGroup, @Query("space_code") String spaceCode);
}
