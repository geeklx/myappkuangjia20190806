package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

//绘本分页查询 传参{"limit": 10, "page": 1, "keyword": {"bookHot":0}} bookHot:是否热书(0:是,1不是);bookClassifyCode:绘本分类Code;bookcaseUserId:我的书架 传UserId
public class SlbHotModel3 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int limit;
    private int page;
    private SlbHotModel33 keyword;

    public SlbHotModel3() {
    }

    public SlbHotModel3(int limit, int page, SlbHotModel33 keyword) {
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

    public SlbHotModel33 getKeyword() {
        return keyword;
    }

    public void setKeyword(SlbHotModel33 keyword) {
        this.keyword = keyword;
    }
}
