package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/4 2:39 PM
 */
@Data
public class IdaasOrgInfoResponse {

   /**
    * 组织标识
    */
   private String code;

   /**
    * 组织名称
    */
   private String name;

   /**
    * 备注
    */
   private String remark;

   /**
    * 隔离空间id
    */
   private String spaceId;

   /**
    * 组织管理者
    */
   private String ownerUid;

   /**
    * 创建时间
    */
   private String gmtCreate;

   /**
    * 是否启用 0.启用 1.未启用
    */
   private Integer status;

   /**
    * 父组织标识
    */
   private String parentCode;

   /**
    * 父组织名称
    */
   private String parentName;
}
