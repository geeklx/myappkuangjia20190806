package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SLB2CategoryListBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<SLB2CategoryListBean1> list;
    private SLB2CategoryListBean2 dataExt;// 背景图片

    public SLB2CategoryListBean() {
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

    public List<SLB2CategoryListBean1> getList() {
        return list;
    }

    public void setList(List<SLB2CategoryListBean1> list) {
        this.list = list;
    }

    public SLB2CategoryListBean2 getDataExt() {
        return dataExt;
    }

    public void setDataExt(SLB2CategoryListBean2 dataExt) {
        this.dataExt = dataExt;
    }
}
