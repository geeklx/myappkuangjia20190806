package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SLB1SaveFavoritesBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String msg;

    public SLB1SaveFavoritesBean() {
    }

    public SLB1SaveFavoritesBean(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
