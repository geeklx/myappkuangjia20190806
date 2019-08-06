package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SLBThreeMusicUrlBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String thirdUrl;

    public SLBThreeMusicUrlBean() {
    }

    public String getThirdUrl() {
        return thirdUrl;
    }

    public void setThirdUrl(String thirdUrl) {
        this.thirdUrl = thirdUrl;
    }
}
