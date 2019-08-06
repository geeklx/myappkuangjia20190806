package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;
import java.util.List;

public class SlbDetailBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<SlbDetailBean2> list;

    public SlbDetailBean1() {
    }

    public SlbDetailBean1(int totalCount, int pageSize, int totalPage, int currPage, List<SlbDetailBean2> list) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = totalPage;
        this.currPage = currPage;
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<SlbDetailBean2> getList() {
        return list;
    }

    public void setList(List<SlbDetailBean2> list) {
        this.list = list;
    }
}
