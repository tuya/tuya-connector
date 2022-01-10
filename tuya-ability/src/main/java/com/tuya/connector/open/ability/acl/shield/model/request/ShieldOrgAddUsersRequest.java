package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 3:33 PM
 */
@Data
public class ShieldOrgAddUsersRequest {

   private List<String> uids;
}
