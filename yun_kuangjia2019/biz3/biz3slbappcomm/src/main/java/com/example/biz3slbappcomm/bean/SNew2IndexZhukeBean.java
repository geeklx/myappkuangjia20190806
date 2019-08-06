package com.example.biz3slbappcomm.bean;

import java.io.Serializable;
import java.util.List;


public class SNew2IndexZhukeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<SNew2IndexZhukeBean1> data;

    public SNew2IndexZhukeBean() {
    }

    public List<SNew2IndexZhukeBean1> getData() {
        return data;
    }

    public void setData(List<SNew2IndexZhukeBean1> data) {
        this.data = data;
    }
}
