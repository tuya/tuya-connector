package com.tuya.connector.open.ability.common;

import lombok.Data;

import java.util.List;

/**
 * @author 哲也
 * @className AbilityPage
 * @desc
 * @since 2021/12/16
 */
@Data
public class AbilityPage<T> {

    /**
     * 是否还有下一页
     */
    private Boolean hasMore;

    /**
     * 分页游标
     */
    private String lastRowKey;

    /**
     * 分页数据
     */
    private List<T> list;
}
