package com.tuya.connector.open.ability.asset.connector;

import com.tuya.connector.api.annotations.*;
import com.tuya.connector.open.ability.asset.model.request.AssetModifyRequest;
import com.tuya.connector.open.ability.asset.model.response.Assets;
import com.tuya.connector.open.ability.common.AbilityPage;
import com.tuya.connector.open.ability.device.model.request.DeviceCommandRequest;

import java.util.List;

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
    String addAsset(@Body AssetModifyRequest request);


}
