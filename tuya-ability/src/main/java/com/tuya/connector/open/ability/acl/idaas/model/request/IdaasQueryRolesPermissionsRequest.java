package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 10:58 AM
 */
@Data
public class IdaasQueryRolesPermissionsRequest {

    private List<String> roleCodeList;
}
