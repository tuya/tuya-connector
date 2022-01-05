package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 9:15 AM
 */
@Data
public class IdaasUpdateOrgRequest {

   private String code;

   private String name;

   private String parentCode;

   private String remark;
}
