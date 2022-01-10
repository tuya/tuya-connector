package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/4 2:43 PM
 */
@Data
public class ShieldUpdateOrgRequest {

    private String organizationName;

    private String uid;

    private String parentCode;

    private String remark;
}
