package com.tuya.connector.open.ability.asset.model.response;

import lombok.Data;

import java.util.List;

@Data
public class GetDevicesByAssetIdResponse {
    private List<GetDevicesByAssetIdItensResponse> list;
    private String last_row_key;
    private Integer total_size;
    private Integer page_size;
    private Boolean has_next;
}
