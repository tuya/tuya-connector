package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 9:21 AM
 */
@Data
public class IdaasOrgBindUsersRequest {

    private List<String> uidList;

    private String organizationCode;
}
