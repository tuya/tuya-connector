package com.tuya.connector.open.ability.acl.idaas.model;

import lombok.Data;

import java.util.List;

/**
 * 分页对象
 *
 * @author Mario
 * @since 2022/1/4 2:37 PM
 */
@Data
public class IdaasPage<T> {

   /**
    * 总数量
    */
   private Integer totalCount;

   /**
    * 总页数
    */
   private Integer totalPages;

   /**
    * 当前页码
    */
   private Integer pageNumber;

   /**
    * 每页容量
    */
   private Integer pageSize;

   /**
    * 结果集
    */
   List<T> results;
}
