package com.situ.javaWeb.util;

import java.util.ArrayList;

public class pageInfo<T> {
    private ArrayList<T> list;
    private int pageNumber;//当前第几页
    private int totalPage;//一共有多少页
    private int pageSize;//一页有多少数据

    public pageInfo() {
        System.out.println("pageInfo.pageInfo");
    }

    public pageInfo(ArrayList<T> list, int pageNumber, int totalPage, int pageSize) {
        this.list = list;
        this.pageNumber = pageNumber;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "pageInfo{" +
                "list=" + list +
                ", pageNumber=" + pageNumber +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                '}';
    }

}
