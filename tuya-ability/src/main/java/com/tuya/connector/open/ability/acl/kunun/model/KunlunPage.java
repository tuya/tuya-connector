package com.tuya.connector.open.ability.acl.kunun.model;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/20 10:33 AM
 */
@Data
public class KunlunPage<T> {

   private Integer pageCount;

   private Integer pageSize;

   private Integer currentPage;

   private Integer recordsCount;

   private List<T> list;
}
