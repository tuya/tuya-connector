package com.tuya.connector.open.ability.common;

import lombok.Data;

import java.util.List;

/**
 * 分页对象
 *
 * @author Mario
 * @since 2022/1/4 2:37 PM
 */
@Data
public class AbilitySizePage<T> {

   private Integer pageNumber;

   private Integer pageSize;

   private Integer totalCount;

   private Integer totalPages;

   List<T> results;
}
