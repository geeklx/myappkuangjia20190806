package com.example.biz3slbappcomm.bean;

import java.io.Serializable;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class SMyMedalCoolViewBean2 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String hios;
    private String imgUrl;
    private String medalId;
    private String medalType;
    private String status;

    public SMyMedalCoolViewBean2() {
    }

    public SMyMedalCoolViewBean2(String hios, String imgUrl, String medalId, String medalType, String status) {
        this.hios = hios;
        this.imgUrl = imgUrl;
        this.medalId = medalId;
        this.medalType = medalType;
        this.status = status;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMedalId() {
        return medalId;
    }

    public void setMedalId(String medalId) {
        this.medalId = medalId;
    }

    public String getMedalType() {
        return medalType;
    }

    public void setMedalType(String medalType) {
        this.medalType = medalType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
