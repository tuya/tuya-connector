package com.tuya.connector.open.ability.acl.idaas.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/4 2:40 PM
 */
@Data
public class IdaasQueryOrgRequest {

   /**
    * 组织标识
    */
   private String code;

   /**
    * 组织标识集合
    */
   private List<String> codes;

   /**
    * 组织名
    */
   private String name;

   /**
    * 父组织标识
    */
   private String parentCode;

   /**
    * 组织备注
    */
   private String remark;

   /**
    * 组织状态
    */
   private String status;

   /**
    * 页码
    */
   private Integer pageNumber;

   /**
    * 页面大小
    */
   private Integer pageSize;


}
