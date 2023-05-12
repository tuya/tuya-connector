package com.tuya.connector.open.ability.asset.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description  TODO
 *
 * @author mario gozzi neto <mariogneto@gmail.com>
 * @date 2023/1/27
 */
@Data
public class AssetRequest implements Serializable {

    private static final long serialVersionUID = 155965236652121243L;
    private String name;
    private String meta_id;
    private String parent_asset_id;
}
