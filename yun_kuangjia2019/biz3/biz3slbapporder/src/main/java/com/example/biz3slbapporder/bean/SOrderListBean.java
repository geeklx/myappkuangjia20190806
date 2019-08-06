package com.example.biz3slbapporder.bean;

import java.io.Serializable;
import java.util.List;


public class SOrderListBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SOrderListBean1> list;

    public SOrderListBean() {
    }

    public List<SOrderListBean1> getList() {
        return list;
    }

    public void setList(List<SOrderListBean1> list) {
        this.list = list;
    }
}
