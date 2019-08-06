package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SIndexAdvertisingBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SIndexAdvertisingBean1 miniActivityView;

    public SIndexAdvertisingBean() {
    }

    public SIndexAdvertisingBean(SIndexAdvertisingBean1 miniActivityView) {
        this.miniActivityView = miniActivityView;
    }

    public SIndexAdvertisingBean1 getMiniActivityView() {
        return miniActivityView;
    }

    public void setMiniActivityView(SIndexAdvertisingBean1 miniActivityView) {
        this.miniActivityView = miniActivityView;
    }
}
