package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

@Data
public class IdaasAddOrgRequest {

    /**
     * 组织标识
     */
    private String code;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 父组织标识
     */
    private String parentCode;

    /**
     * 组织备注
     */
    private String remark;

    /**
     * 组织管理者
     */
    private String ownerUid;

    private Integer status;
}
