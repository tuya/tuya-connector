package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

@Data
public class IdaasOrgRequest {
    /**
     * code
     */
    private String code;

    /**
     * name
     */
    private String name;

    /**
     * parentCode
     */
    private String parentCode;

    /**
     * remark
     */
    private String remark;

    /**
     * ownerUid
     */
    private String ownerUid;
}
