package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldUserCheckPermissionRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldPermissionInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:51 PM
 */
public interface ShieldUserPermissionConnector {

   @GET("/v1.0/iot-02/users/{uid}/permissions")
   List<ShieldPermissionInfoResponse> queryPermissionsByUid(@Path("uid") String uid);

   @POST("/v1.0/iot-02/users/{uid}/actions/permissions-valid")
   Boolean checkUserPermission(@Path("uid") String uid,
                               @Body ShieldUserCheckPermissionRequest req);
}
