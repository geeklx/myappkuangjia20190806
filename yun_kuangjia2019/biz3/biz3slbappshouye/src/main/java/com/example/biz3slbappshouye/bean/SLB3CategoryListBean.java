package com.example.biz3slbappshouye.bean;

import java.io.Serializable;
import java.util.List;

public class SLB3CategoryListBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SLB4CategoryListDetailBean1> list;
    private SLB2CategoryListBean2 dataExt;// 背景图片

    public SLB3CategoryListBean() {
    }

    public List<SLB4CategoryListDetailBean1> getList() {
        return list;
    }

    public void setList(List<SLB4CategoryListDetailBean1> list) {
        this.list = list;
    }

    public SLB2CategoryListBean2 getDataExt() {
        return dataExt;
    }

    public void setDataExt(SLB2CategoryListBean2 dataExt) {
        this.dataExt = dataExt;
    }
}
