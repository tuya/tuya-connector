package com.tuya.connector.open.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description  the page result with total records
 *
 * @author Medivh.chen@tuya.com
 * @date 2021/4/20
 */
@Data
public class PageResultWithTotal<T> implements Serializable {


    /**
     * returned data
     */
    private List<T> list;

    /**
     * whether has next page
     */
    private Boolean hasMore;

    /**
     * the total number of records
     */
    private int total;
}
