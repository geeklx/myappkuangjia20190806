package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SListCommBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<SListCommBean1> list;
    private SListCommBean2 dataExt;// 背景图片

    public SListCommBean() {
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

    public List<SListCommBean1> getList() {
        return list;
    }

    public void setList(List<SListCommBean1> list) {
        this.list = list;
    }

    public SListCommBean2 getDataExt() {
        return dataExt;
    }

    public void setDataExt(SListCommBean2 dataExt) {
        this.dataExt = dataExt;
    }
}
