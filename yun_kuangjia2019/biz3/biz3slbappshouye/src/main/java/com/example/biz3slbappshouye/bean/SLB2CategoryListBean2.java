package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

public class SLB2CategoryListBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String screenImg;// 背景图片

    public SLB2CategoryListBean2() {
    }

    public SLB2CategoryListBean2(String screenImg) {
        this.screenImg = screenImg;
    }

    public String getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(String screenImg) {
        this.screenImg = screenImg;
    }
}
