package com.example.biz3slbappshouye.bean;

import java.io.Serializable;

//	"currPage": 0,
//            "list": [
//            {
//            "appConfId": 0,
//            "bannerImg": "",
//            "bookId": 0,
//            "bookItemId": 0,
//            "bookName": "",
//            "itemCount": 0,
//            "orders": 0,
//            "readmode": "",
//            "sourceType": "",
//            "vertical": false
//            }
//            ],
//            "pageSize": 0,
//            "totalCount": 0,
//            "totalPage": 0
public class SListCommBean1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String appConfId;
    private String bannerImg;
    private String bookId;
    private String bookItemId;
    private String bookName;
    private String descr;
    private String collect;
    private String cornerType;
    private SListCommBean3 cornerMap;
    private String coverImg;
    private String itemCount;
    private String itemCountStr;
    private String orders;
    private String readRight;
    private String readmode;
    private String sourceType;
    private String hios;
    private String screenImg;
    private boolean vertical;
    private boolean isRetweet;

    public SListCommBean1() {
    }

    public String getAppConfId() {
        return appConfId;
    }

    public void setAppConfId(String appConfId) {
        this.appConfId = appConfId;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getCornerType() {
        return cornerType;
    }

    public void setCornerType(String cornerType) {
        this.cornerType = cornerType;
    }

    public SListCommBean3 getCornerMap() {
        return cornerMap;
    }

    public void setCornerMap(SListCommBean3 cornerMap) {
        this.cornerMap = cornerMap;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemCountStr() {
        return itemCountStr;
    }

    public void setItemCountStr(String itemCountStr) {
        this.itemCountStr = itemCountStr;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getReadRight() {
        return readRight;
    }

    public void setReadRight(String readRight) {
        this.readRight = readRight;
    }

    public String getReadmode() {
        return readmode;
    }

    public void setReadmode(String readmode) {
        this.readmode = readmode;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getHios() {
        return hios;
    }

    public void setHios(String hios) {
        this.hios = hios;
    }

    public String getScreenImg() {
        return screenImg;
    }

    public void setScreenImg(String screenImg) {
        this.screenImg = screenImg;
    }

    public boolean isVertical() {
        return vertical;
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
