package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

public class SListCommBean4 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String imgUrl;//
    private String brandCode;//
    private String position;//

    public SListCommBean4() {
    }

    public SListCommBean4(String imgUrl, String brandCode, String position) {
        this.imgUrl = imgUrl;
        this.brandCode = brandCode;
        this.position = position;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
