package com.tuya.connector.open.ability.acl.idaas.model.response;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:50 AM
 */
@Data
public class IdaasPermissionInfoResponse {

   /**
    * 权限标识
    */
   private String permissionCode;

   /**
    * 显示名称
    */
   private String name;

   private Integer type;

   /**
    * 父级权限code
    */
   private String parentCode;

   /**
    * 展示顺序
    */
   private Integer order;

   /**
    * 备注
    */
   private String remark;

   /**
    * 权限空间id
    * */
   private String spaceId;
}
