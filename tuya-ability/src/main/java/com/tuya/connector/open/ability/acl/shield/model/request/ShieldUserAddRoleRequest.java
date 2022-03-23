package com.tuya.connector.open.ability.acl.shield.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 4:13 PM
 */
@Data
public class ShieldUserAddRoleRequest {

   private List<String> roleCodes;
}
