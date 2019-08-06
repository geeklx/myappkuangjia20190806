package com.example.biz3slbappusercenter.bean;

import java.io.Serializable;

public class SWelcomeSettingBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String key;
    private String value;

    public SWelcomeSettingBean1() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
