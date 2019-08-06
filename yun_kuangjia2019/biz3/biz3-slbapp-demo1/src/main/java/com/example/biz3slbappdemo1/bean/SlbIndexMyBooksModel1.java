package com.example.biz3slbappdemo1.bean;

import java.io.Serializable;

public class SlbIndexMyBooksModel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String imei;

    public SlbIndexMyBooksModel1() {
    }

    public SlbIndexMyBooksModel1(String imei) {
        this.imei = imei;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
