package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbDetailModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int limit;
    private int page;
    private SlbDetailModel1 keyword;

    public SlbDetailModel() {
    }

    public SlbDetailModel(int limit, int page, SlbDetailModel1 keyword) {
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

    public SlbDetailModel1 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbDetailModel1 keyword) {
        this.keyword = keyword;
    }
}
