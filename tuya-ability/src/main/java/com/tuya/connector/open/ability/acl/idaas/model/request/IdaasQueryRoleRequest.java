package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/5 11:28 AM
 */
@Data
public class IdaasQueryRoleRequest {

   private String roleCode;

   private String roleName;

   private Boolean gmtModifiedAsc;

   private Integer pageNumber;

   private Integer pageSize;
}
