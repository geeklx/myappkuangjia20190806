package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

// "bannerList": [
//         {
//         "bannerImg": "https://edu-admin.oss-cn-hangzhou.aliyuncs.com/20181126/3890543e5a7e4e9684b5c895f55a119f.jpg",
//         "itemCount": null,
//         "appConfId": "1",
//         "bookName": "冰雪奇缘-01",
//         "vertical": false,
//         "readmode": "across",
//         "bookId": "2",
//         "bookItemId": "2",
//         "sourceType": "bookItem",
//         "orders": 4
//         },
public class SLBBannerBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bannerImg;
    private String hios;
    private String itemId;
    private String pId;
    private String sourceType;

    public SLBBannerBean1() {
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }
}
