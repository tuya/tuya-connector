package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 1:13 PM
 */
@Data
public class IdaasAccountUsersResponse {

    private List<IdaasAccountUserResponse> users;
}
