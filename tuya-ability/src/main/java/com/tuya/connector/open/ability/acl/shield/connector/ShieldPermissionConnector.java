package com.tuya.connector.open.ability.acl.shield.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.GET;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.api.annotations.Query;
import com.tuya.connector.open.ability.acl.shield.model.request.ShieldBatchAddPermissionsRequest;
import com.tuya.connector.open.ability.acl.shield.model.response.ShieldPermissionInfoResponse;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:22 PM
 */
public interface ShieldPermissionConnector {

    @POST("/v1.0/iot-02/permissions")
    Boolean batchAddPermissions(@Body ShieldBatchAddPermissionsRequest req);

    @DELETE("/v1.0/iot-02/permissions/{permission_code}")
    Boolean deletePermission(@Path("permission_code") String permissionCode);

    @GET("/v1.0/iot-02/permissions")
    List<ShieldPermissionInfoResponse> queryPermissions(@Query("permission_codes") String permissionCodes);
}
