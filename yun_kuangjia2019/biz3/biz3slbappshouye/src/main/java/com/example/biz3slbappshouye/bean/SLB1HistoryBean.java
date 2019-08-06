package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SLB1HistoryBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<SLB1HistoryBean1> list;

    public SLB1HistoryBean() {
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

    public List<SLB1HistoryBean1> getList() {
        return list;
    }

    public void setList(List<SLB1HistoryBean1> list) {
        this.list = list;
    }
}
