package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SUserImgBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String url;

    public SUserImgBean() {
    }

    public SUserImgBean(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
