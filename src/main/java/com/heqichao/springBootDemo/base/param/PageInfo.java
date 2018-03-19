package com.heqichao.springBootDemo.base.param;

/**
 * Created by heqichao on 2018-2-14.
 */
public class PageInfo {

    private int size;

    private int pageSize;

    public PageInfo(){}

    public PageInfo(int size, int pageSize) {
        this.size = size;
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
