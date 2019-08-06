package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbIndexMyBooksModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int limit;
    private int page;
    private SlbIndexMyBooksModel1 keyword;

    public SlbIndexMyBooksModel() {
    }

    public SlbIndexMyBooksModel(int limit, int page, SlbIndexMyBooksModel1 keyword) {
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

    public SlbIndexMyBooksModel1 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbIndexMyBooksModel1 keyword) {
        this.keyword = keyword;
    }
}
