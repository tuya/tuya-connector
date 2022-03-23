package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:09 AM
 */
@Data
public class IdaasQueryUserRequest {

    private String username;

    private String roleName;

    private String roleCode;

    /**
     * 是否按修改时间逆序
     */
    private Boolean gmtModifiedAsc;

    private Integer pageNumber;

    private Integer pageSize;
}
