package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

// 支付宝
public class SPayBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderStr;

    public SPayBean2() {
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }
}
