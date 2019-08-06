package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SMyMedalDetailShareBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SMyMedalDetailShareBean1 details;

    public SMyMedalDetailShareBean() {
    }

    public SMyMedalDetailShareBean1 getDetails() {
        return details;
    }

    public void setDetails(SMyMedalDetailShareBean1 details) {
        this.details = details;
    }
}
