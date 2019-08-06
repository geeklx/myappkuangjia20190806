package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SMyMedalDetailBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SMyMedalDetailBean1 details;

    public SMyMedalDetailBean() {
    }

    public SMyMedalDetailBean1 getDetails() {
        return details;
    }

    public void setDetails(SMyMedalDetailBean1 details) {
        this.details = details;
    }
}
