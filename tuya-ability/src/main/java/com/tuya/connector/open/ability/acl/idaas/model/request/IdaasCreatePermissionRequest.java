package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

/**
 * @author Mario
 * @since 2022/1/5 10:42 AM
 */
@Data
public class IdaasCreatePermissionRequest {

   /**
    * 权限标识
    */
   private String permissionCode;

   /**
    * 显示名称
    */
   private String name;

   /**
    * 权限类型(api/menu/button/data)
    */
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
}
