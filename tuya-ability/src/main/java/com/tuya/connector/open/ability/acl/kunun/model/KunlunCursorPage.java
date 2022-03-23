package com.tuya.connector.open.ability.acl.kunun.model;

import lombok.Data;

import java.util.List;

/**
 * @author Mario
 * @since 2022/1/20 10:26 AM
 */
@Data
public class KunlunCursorPage<T> {

   private Integer pageSize;

   private Boolean hasNext;

   private String lastRowKey;

   private List<T> list;
}
