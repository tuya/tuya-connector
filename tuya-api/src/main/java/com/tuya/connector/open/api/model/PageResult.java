package com.tuya.connector.open.api.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description  TODO
 *
 * @author Chyern
 * @date 2021/3/26
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -1379646557144992944L;

    /**
     * returned data
     */
    private List<T> list;

    /**
     * the next page is exist or not
     */
    private Boolean hasNext;

    /**
     * the last asset id where in this page
     */
    private String lastRowKey;

    /**
     * the size of page
     */
    private String pageSize;
}
