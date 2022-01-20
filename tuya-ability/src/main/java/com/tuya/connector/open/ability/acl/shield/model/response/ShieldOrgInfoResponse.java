package com.tuya.connector.open.ability.acl.shield.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/4 2:44 PM
 */
@Data
public class ShieldOrgInfoResponse {

    private String organizationId;

    private String organizationName;

    private String parentId;

    private String remark;

    private String ownerUid;
}
