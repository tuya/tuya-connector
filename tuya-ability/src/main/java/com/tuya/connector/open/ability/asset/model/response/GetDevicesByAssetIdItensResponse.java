package com.tuya.connector.open.ability.asset.model.response;

import lombok.Data;

@Data
public class GetDevicesByAssetIdItensResponse {
    private String device_id;
    private String asset_id;
    private String asset_name;
    private String device_name;
}
