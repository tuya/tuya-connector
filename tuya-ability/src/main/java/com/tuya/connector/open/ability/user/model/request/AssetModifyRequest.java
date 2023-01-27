package com.tuya.connector.open.ability.user.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Description  TODO
 *
 * @author Emerson Gil
 * @date 2023/1/27
 */
@Data
public class AssetModifyRequest implements Serializable {

    private static final long serialVersionUID = 155965236652121243L;
    private String name;
    private String meta_id;
    private String parent_asset_id;
}
