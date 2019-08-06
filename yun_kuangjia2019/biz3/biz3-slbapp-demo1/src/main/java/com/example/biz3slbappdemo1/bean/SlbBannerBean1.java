package com.example.biz3slbappdemo1.bean;

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
public class SlbBannerBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String bannerImg;
    private String itemCount;
    private String appConfId;
    private String bookName;
    private boolean vertical;
    private String readmode;
    private String bookId;
    private String bookItemId;
    private String sourceType;
    private String orders;

    public SlbBannerBean1() {
    }

    public SlbBannerBean1(String bannerImg, String itemCount, String appConfId, String bookName, boolean vertical, String readmode, String bookId, String bookItemId, String sourceType, String orders) {
        this.bannerImg = bannerImg;
        this.itemCount = itemCount;
        this.appConfId = appConfId;
        this.bookName = bookName;
        this.vertical = vertical;
        this.readmode = readmode;
        this.bookId = bookId;
        this.bookItemId = bookItemId;
        this.sourceType = sourceType;
        this.orders = orders;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getAppConfId() {
        return appConfId;
    }

    public void setAppConfId(String appConfId) {
        this.appConfId = appConfId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public String getReadmode() {
        return readmode;
    }

    public void setReadmode(String readmode) {
        this.readmode = readmode;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(String bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
