package com.tuya.connector.open.ability.asset.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description  TODO
 *
 * @author Chyern
 * @date 2021/3/27
 */
@Data
public class AssetModifyRequest implements Serializable {

    private static final long serialVersionUID = 155965236652121243L;
    private String name;
    private String meta_id;
    private String parent_asset_id;
}
