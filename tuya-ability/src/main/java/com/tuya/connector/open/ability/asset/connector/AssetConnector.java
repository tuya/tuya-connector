package com.tuya.connector.open.ability.asset.connector;

import com.tuya.connector.api.annotations.Body;
import com.tuya.connector.api.annotations.DELETE;
import com.tuya.connector.api.annotations.POST;
import com.tuya.connector.api.annotations.Path;
import com.tuya.connector.open.ability.asset.model.request.AssetRequest;
import com.tuya.connector.open.ability.asset.model.request.BatchAssetsAuthorizedRequest;
import com.tuya.connector.open.ability.user.model.request.UserRequest;
import com.tuya.connector.open.ability.user.model.response.AddUserResponse;

/**
 * <p> TODO
 *
 * @author @author mariogneto@gmail.com
 * @since 2023/1/27 10:10
 */
public interface AssetConnector {

    /**
     * delete asset
     *
     * @param assetId
     * @return
     */
    @DELETE("/v1.0/iot-02/assets/{asset_id}")
    Boolean deleteAsset(@Path(("asset_id")) String assetId);

    /**
     * add asset
     *
     * @param request
     * @return
     */
    @POST("/v1.0/iot-02/assets")
    String addAsset(@Body AssetRequest request);


    /**
     * Authorize Assets to a User
     *
     * @param request
     * @return
     */
    @POST("/v1.0/iot-03/users/{user_id}/actions/batch-assets-authorized")
    Boolean batchAssetsAuthorized(@Path(("user_id")) String userId, @Body BatchAssetsAuthorizedRequest request);


}
