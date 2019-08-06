package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;

public class SNew2IndexLianxiBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SNew2IndexLianxiBean1> data;

    public SNew2IndexLianxiBean() {
    }

    public List<SNew2IndexLianxiBean1> getData() {
        return data;
    }

    public void setData(List<SNew2IndexLianxiBean1> data) {
        this.data = data;
    }
}
