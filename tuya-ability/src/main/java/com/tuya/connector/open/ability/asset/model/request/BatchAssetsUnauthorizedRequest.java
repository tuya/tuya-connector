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
public class BatchAssetsUnauthorizedRequest implements Serializable {

    private Boolean authorized_children;
    private String asset_ids;
}
