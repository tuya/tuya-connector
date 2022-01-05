package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 9:26 AM
 */
@Data
public class IdaasOrgQueryUserRequest {

   private String organizationCode;

   /**
    * 用户标识
    */
   private String uid;

   /**
    * 页面大小
    */
   private Integer pageSize;

   /**
    * 页码
    */
   private Integer pageNumber;
}
