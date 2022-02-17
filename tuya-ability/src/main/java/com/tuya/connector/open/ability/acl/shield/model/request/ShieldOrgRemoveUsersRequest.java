package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author mgy
 * @since 2022/2/17 3:33 PM
 */
@Data
public class ShieldOrgRemoveUsersRequest {

   private List<String> uids;
}
