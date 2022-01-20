package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 11:16 AM
 */
@Data
public class IdaasCreateRoleRequest {

   /**
    * 角色code
    */
   String roleCode;

   /**
    * 角色名字
    */
   String roleName;

   /**
    * 备注
    */
   String remark;
}
