package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/4 2:28 PM
 */
@Data
public class ShieldAddOrgRequest {

    private String organizationId;

    private String organizationName;

    private String parentId;

    private String remark;

    private String ownerId;
}
