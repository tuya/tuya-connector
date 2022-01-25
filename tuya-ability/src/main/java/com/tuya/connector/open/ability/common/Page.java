package com.tuya.connector.open.ability.common;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/10 3:19 PM
 */
@Data
public class Page<T> {

   /**
    * 总数据量
    */
   private Long totalCount;

   /**
    * 是否有更多
    */
   private Boolean hasMore;

   /**
    * 数据集
    */
   private List<T> list;
}
