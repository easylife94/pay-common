package com.pay.common.client.dto;

/**
 * 查询基类
 *
 * @author chenwei
 * @date 2019/5/15 11:10
 */
public abstract class QueryBase {

    private Long pageNumber;

    private Long pageSize;

    private Long start;

    public Long getPageNumber() {
        return pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getStart() {
        return (pageNumber - 1) * pageSize;
    }
}
