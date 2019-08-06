package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbHotModel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int limit;
    private int page;
    private SlbHotModel11 keyword;

    public SlbHotModel1() {
    }

    public SlbHotModel1(int limit, int page, SlbHotModel11 keyword) {
        this.limit = limit;
        this.page = page;
        this.keyword = keyword;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public SlbHotModel11 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbHotModel11 keyword) {
        this.keyword = keyword;
    }
}
