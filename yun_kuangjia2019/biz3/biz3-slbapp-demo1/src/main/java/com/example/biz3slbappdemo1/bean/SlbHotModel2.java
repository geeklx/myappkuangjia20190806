package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbHotModel2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int limit;
    private int page;
    private SlbHotModel22 keyword;

    public SlbHotModel2() {
    }

    public SlbHotModel2(int limit, int page, SlbHotModel22 keyword) {
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

    public SlbHotModel22 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbHotModel22 keyword) {
        this.keyword = keyword;
    }
}
