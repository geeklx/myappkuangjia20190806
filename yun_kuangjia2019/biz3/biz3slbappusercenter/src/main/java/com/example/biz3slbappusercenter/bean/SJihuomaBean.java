package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SJihuomaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;
    private String msg;

    public SJihuomaBean() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
