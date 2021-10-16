package com.jyc.vo;

public class Page {
    private Integer offset;
    private Integer pageSize;

    public Page() {
    }

    public Page(Integer offset, Integer pageSize) {
        this.offset = offset;
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
