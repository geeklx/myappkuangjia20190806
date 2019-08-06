package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SIndexAdvertisingBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bgImg;
    private String hios;
    private String id;

    public SIndexAdvertisingBean1() {
    }

    public SIndexAdvertisingBean1(String bgImg, String hios, String id) {
        this.bgImg = bgImg;
        this.hios = hios;
        this.id = id;
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
