package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SWelcomeSettingBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private SWelcomeSettingBean1 adPage;

    public SWelcomeSettingBean() {
    }

    public SWelcomeSettingBean1 getAdPage() {
        return adPage;
    }

    public void setAdPage(SWelcomeSettingBean1 adPage) {
        this.adPage = adPage;
    }
}
